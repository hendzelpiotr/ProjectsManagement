package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.domain.general.Project;
import com.project.java.prz.common.core.domain.general.UserProject;
import com.project.java.prz.common.core.domain.security.RoleType;
import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.common.core.dto.UserProjectDTO;
import com.project.java.prz.common.core.exception.ProjectException;
import com.project.java.prz.common.core.exception.UserProjectException;
import com.project.java.prz.common.core.mapper.UserProjectMapper;
import com.project.java.prz.server.core.repository.ProjectRepository;
import com.project.java.prz.server.core.repository.UserProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Piotr on 17.04.2017.
 */
@Service
@Transactional
public class UserProjectServiceImpl implements UserProjectService {

    @Autowired
    private Clock clock;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Override
    public List<UserProjectDTO> getAll() {
        List<UserProject> userProjects = userProjectRepository.findAll();
        List<UserProjectDTO> userProjectDTOs = UserProjectMapper.INSTANCE.convertToDTOs(userProjects);

        userProjectDTOs = userProjectDTOs.stream().map(userProjectDTO -> {
            userProjectDTO.setUserDTO(userService.getOne(userProjectDTO.getUserDTO().getId()));
            return userProjectDTO;
        }).collect(Collectors.toList());

        return userProjectDTOs;
    }

    @Override
    public UserProjectDTO getUserProjectOfCurrentlyLoggedInUser(String login) {
        UserDTO userDTO = getUser(login);
        UserProject userProject = userProjectRepository.findByUserId(userDTO.getId());

        if (userProject != null) {
            UserProjectDTO userProjectDTO = UserProjectMapper.INSTANCE.convertToDTO(userProject);
            userProjectDTO.setUserDTO(userDTO);
            return userProjectDTO;
        } else throw new UserProjectException(UserProjectException.FailReason.USER_PROJECT_NOT_FOUND);
    }

    @Override
    public UserProjectDTO assignProjectToStudent(String login, Integer projectId) {
        UserDTO userDTO = getUser(login);
        Project project = projectRepository.getOne(projectId);

        isExisting(project);

        Integer availableProjectsCounter = project.getAvailableProjectsCounter();

        UserProject userProject = userProjectRepository.findByUserId(userDTO.getId());
        if (userProject == null
                && (availableProjectsCounter == null || availableProjectsCounter > 0)) {
            UserProject userProjectToSave = new UserProject();
            userProjectToSave.setProject(project);
            userProjectToSave.setUserId(userDTO.getId());
            userProjectToSave.setDateTimeOfProjectSelection(LocalDateTime.now(clock));
            userProjectToSave = userProjectRepository.save(userProjectToSave);

            if (availableProjectsCounter != null) {
                project.setAvailableProjectsCounter(availableProjectsCounter - 1);
                projectRepository.save(project);
            }

            return UserProjectMapper.INSTANCE.convertToDTO(userProjectToSave);
        } else {
            throw new UserProjectException(UserProjectException.FailReason.YOU_ALREADY_CHOSE_PROJECT);
        }

    }

    private void isExisting(Project project) {
        if (project == null)
            throw new ProjectException(ProjectException.FailReason.PROJECT_DOES_NOT_EXIST);
    }

    @Override
    public void deleteById(String login, Integer id) {
        UserDTO userDTO = getUser(login);

        if (isAdmin(userDTO) || isPossibleToRemove(id, userDTO)) {
            userProjectRepository.delete(id);
        } else throw new UserProjectException(UserProjectException.FailReason.YOU_CAN_NOT_ABANDON_PROJECT);
    }

    @Override
    public UserProjectDTO update(String login, UserProjectDTO userProjectDTO) {
        UserDTO userDTO = getUser(login);
        UserProject userProject = new UserProject();

        switch (userDTO.getRoleDTO().getName()) {
            case ROLE_ADMIN:
                userProject = prepareToUpdateByAdmin(userProjectDTO);
                break;
            case ROLE_STUDENT:
                userProject = userProjectRepository.findByUserId(userDTO.getId());
                if (userProject != null && userProject.getId().equals(userProjectDTO.getId())) {
                    userProject = prepareToUpdateByStudent(userProjectDTO, userProject);
                } else throw new UserProjectException(UserProjectException.FailReason.YOU_CAN_NOT_UPDATE_USER_PROJECT);
                break;
        }

        UserProject updatedUserProject = userProjectRepository.save(userProject);
        return UserProjectMapper.INSTANCE.convertToDTO(updatedUserProject);
    }

    private UserProject prepareToUpdateByStudent(UserProjectDTO dto, UserProject dbUserProject) {
        if (!isAfterScheduledCompletionDateTime(dbUserProject.getScheduledCompletionDateTime())) {
            dbUserProject.setProgrammingLanguage(dto.getProgrammingLanguage());
            dbUserProject.setTechnologies(dto.getTechnologies());
            dbUserProject.setDatabase(dto.getDatabase());
            dbUserProject.setRepositoryLink(dto.getRepositoryLink());
            dbUserProject.setAdditionalInformation(dto.getAdditionalInformation());
            dbUserProject.setSourceFilesUploaded(dto.isSourceFilesUploaded());
            return dbUserProject;
        } else throw new UserProjectException(UserProjectException.FailReason.YOU_CAN_NOT_UPDATE_USER_PROJECT);
    }

    private boolean isAfterScheduledCompletionDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return false;
        }
        return LocalDateTime.now(clock).isAfter(dateTime);
    }

    private UserProject prepareToUpdateByAdmin(UserProjectDTO dto) {
        return UserProjectMapper.INSTANCE.convertToEntity(dto);
    }

    private boolean isAdmin(UserDTO userDTO) {
        return RoleType.ROLE_ADMIN.equals(userDTO.getRoleDTO().getName());
    }

    private boolean isPossibleToRemove(Integer id, UserDTO userDTO) {
        UserProject userProject = userProjectRepository.findByUserId(userDTO.getId());

        return id.equals(userProject.getId())
                && userProject.getScheduledCompletionDateTime() == null
                && LocalDateTime.now(clock).isBefore(userProject.getDateTimeOfProjectSelection().plusDays(14));
    }

    private UserDTO getUser(String login) {
        return userService.getOneByLogin(login);
    }

}
