package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.domain.Project;
import com.project.java.prz.common.core.domain.RoleType;
import com.project.java.prz.common.core.domain.User;
import com.project.java.prz.common.core.domain.UserProject;
import com.project.java.prz.common.core.dto.UserProjectDTO;
import com.project.java.prz.common.core.exception.ProjectException;
import com.project.java.prz.common.core.exception.UserProjectException;
import com.project.java.prz.common.core.mapper.UserProjectMapper;
import com.project.java.prz.server.core.repository.ProjectRepository;
import com.project.java.prz.server.core.repository.UserProjectRepository;
import com.project.java.prz.server.core.repository.UserRepository;
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
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Override
    public List<UserProjectDTO> getAll() {
        return UserProjectMapper.INSTANCE.convertToDTOs(userProjectRepository.findAll());
    }

    @Override
    public UserProjectDTO getUserProjectOfCurrentlyLoggedInUser(String login) {
        UserProject userProject = userProjectRepository.findByUserLogin(login);
        if (userProject != null) {
            return UserProjectMapper.INSTANCE.convertToDTO(userProject);
        } else throw new UserProjectException(UserProjectException.FailReason.USER_PROJECT_NOT_FOUND);
    }

    @Override
    public UserProjectDTO assignProjectToStudent(String login, Integer projectId) {
        User user = userRepository.findByLogin(login);
        Project project = projectRepository.getOne(projectId);

        isExisting(project);

        Integer availableProjectsCounter = project.getAvailableProjectsCounter();

//        if (user.getUserProject() == null
//                && (availableProjectsCounter == null || availableProjectsCounter > 0)) {
//            UserProject userProject = new UserProject();
//            userProject.setProject(project);
//            userProject.setUser(user);
//            userProject.setDatetimeOfProjectSelection(LocalDateTime.now());
//            userProject = userProjectRepository.save(userProject);
//
//            if (availableProjectsCounter != null) {
//                project.setAvailableProjectsCounter(availableProjectsCounter - 1);
//                projectRepository.save(project);
//            }
//
//            return UserProjectMapper.INSTANCE.convertToDTO(userProject);
//        } else {
//            throw new UserProjectException(UserProjectException.FailReason.YOU_ALREADY_CHOSE_PROJECT);
//        }
    return null;
    }

    private void isExisting(Project project) {
        if (project == null)
            throw new ProjectException(ProjectException.FailReason.PROJECT_DOES_NOT_EXIST);
    }

    @Override
    public void deleteById(String login, Integer id) {
        User user = userRepository.findByLogin(login);

//        if (isAdmin(user) || isPossibleToRemove(id, user)) {
//            userProjectRepository.delete(id);
//        } else throw new UserProjectException(UserProjectException.FailReason.YOU_CAN_NOT_ABANDON_PROJECT);
    }

    @Override
    public UserProjectDTO update(String login, UserProjectDTO userProjectDTO) {
        User user = userRepository.findByLogin(login);
        UserProject userProject = new UserProject();

        switch (user.getRole().getName()) {
            case ROLE_ADMIN:
                userProject = prepareToUpdateByAdmin(userProjectDTO);
                break;
            case ROLE_STUDENT:
                //userProject = user.getUserProject();
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

    private boolean isAdmin(User user) {
        return RoleType.ROLE_ADMIN.equals(user.getRole().getName());
    }

//    private boolean isPossibleToRemove(Integer id, User user) {
//        ///UserProject userProject = user.getUserProject();
//
//        return id.equals(userProject.getId())
//                && userProject.getCompletionDateTime() == null
//                && userProject.getDatetimeOfProjectSelection().isBefore(userProject.getDatetimeOfProjectSelection().plusDays(14));
//    }

}
