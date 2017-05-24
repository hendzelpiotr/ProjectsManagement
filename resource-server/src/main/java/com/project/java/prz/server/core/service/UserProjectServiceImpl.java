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
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Piotr on 17.04.2017.
 */
@Service
@Transactional
public class UserProjectServiceImpl implements UserProjectService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Override
    public List<UserProjectDTO> getAll() {
        return UserProjectMapper.INSTANCE.convertToDTOs(userProjectRepository.findAll());
    }

    @Override
    public UserProjectDTO getUserProjectOfCurrentlyLoggedInUser(String login) {
        UserDTO userDTO = getUser(login);
        UserProject userProject = userProjectRepository.findByUserId(userDTO.getId());
        if (userProject != null) {
            return UserProjectMapper.INSTANCE.convertToDTO(userProject);
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
            userProjectToSave.setDatetimeOfProjectSelection(LocalDateTime.now());
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
        if (!dbUserProject.isReadyToGrade()) {
            dbUserProject.setProgrammingLanguage(dto.getProgrammingLanguage());
            dbUserProject.setTechnologies(dto.getTechnologies());
            dbUserProject.setDatabase(dto.getDatabase());
            dbUserProject.setRepositoryLink(dto.getRepositoryLink());
            dbUserProject.setAdditionalInformation(dto.getAdditionalInformation());
            dbUserProject.setSourceFilesUploaded(dto.isSourceFilesUploaded());
        } else throw new UserProjectException(UserProjectException.FailReason.YOU_CAN_NOT_UPDATE_USER_PROJECT);

        if (dto.isReadyToGrade() && !dbUserProject.isReadyToGrade()) {
            if (checkWhetherUserProjectIsReadyToGrade(dbUserProject)) {
                dbUserProject.setReadyToGrade(dto.isReadyToGrade());
                dbUserProject.setCompletionDateTime(LocalDateTime.now());
            } else throw new UserProjectException(UserProjectException.FailReason.YOU_CAN_NOT_UPDATE_USER_PROJECT);
        }

        return dbUserProject;
    }

    private Boolean checkWhetherUserProjectIsReadyToGrade(UserProject userProject) {
        return userProject.isSourceFilesUploaded() && userProject.getTechnologies() != null && userProject.getProgrammingLanguage() != null;
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
                && userProject.getCompletionDateTime() == null
                && userProject.getDatetimeOfProjectSelection().isBefore(userProject.getDatetimeOfProjectSelection().plusDays(14));
    }

    private UserDTO getUser(String login) {
        return userService.getOneByLogin(login);
    }

}
