package com.project.java.prz.service;

import com.project.java.prz.domain.Project;
import com.project.java.prz.domain.RoleType;
import com.project.java.prz.domain.User;
import com.project.java.prz.domain.UserProject;
import com.project.java.prz.dto.UserProjectDTO;
import com.project.java.prz.exception.ProjectException;
import com.project.java.prz.exception.UserProjectException;
import com.project.java.prz.mapper.UserProjectMapper;
import com.project.java.prz.repository.ProjectRepository;
import com.project.java.prz.repository.UserProjectRepository;
import com.project.java.prz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.project.java.prz.exception.UserProjectException.FailReason.YOU_CAN_NOT_ABANDON_PROJECT;

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
        return UserProjectMapper.INSTANCE.convertToDTO(userProjectRepository.findByUserLogin(login));
    }

    @Override
    public UserProjectDTO assignProjectToStudent(String login, Integer projectId) {
        User user = userRepository.findByLogin(login);
        Project project = projectRepository.getOne(projectId);

        isExisting(project);

        Integer availableProjectsCounter = project.getAvailableProjectsCounter();

        if (user.getUserProject() == null
                && (availableProjectsCounter == null || availableProjectsCounter > 0)) {
            UserProject userProject = new UserProject();
            userProject.setProject(project);
            userProject.setUser(user);
            userProject.setDatetimeOfProjectSelection(LocalDateTime.now());
            userProject = userProjectRepository.save(userProject);

            if (availableProjectsCounter != null) {
                project.setAvailableProjectsCounter(availableProjectsCounter - 1);
                projectRepository.save(project);
            }

            return UserProjectMapper.INSTANCE.convertToDTO(userProject);
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
        User user = userRepository.findByLogin(login);

        if (isAdmin(user) || isPossibleToRemove(id, user)) {
            userProjectRepository.delete(id);
        } else throw new UserProjectException(YOU_CAN_NOT_ABANDON_PROJECT);
    }

    @Override
    public UserProjectDTO fillNecessaryInformation(String login, UserProjectDTO userProjectDTO) {
        User user = userRepository.findByLogin(login);
        UserProject userProject = user.getUserProject();

        if (userProject.getId() == userProjectDTO.getId()) {
            userProjectDTO.setMark(userProject.getMark());
            userProjectDTO.setDatetimeOfProjectSelection(userProject.getDatetimeOfProjectSelection());
            userProjectDTO.getProjectDTO().setDescription(userProject.getProject().getDescription());
            userProjectDTO.getProjectDTO().setName(userProject.getProject().getName());

            UserProject updatedUserProject = userProjectRepository.save(UserProjectMapper.INSTANCE.convertToEntity(userProjectDTO));
            return UserProjectMapper.INSTANCE.convertToDTO(updatedUserProject);
        }
        throw new UserProjectException(UserProjectException.FailReason.YOU_CAN_NOT_UPDATE_USER_PROJECT);
    }

    private boolean isAdmin(User user) {
        return RoleType.ROLE_ADMIN.equals(user.getRole().getName());
    }

    private boolean isPossibleToRemove(Integer id, User user) {
        UserProject userProject = user.getUserProject();

        return id == userProject.getId() && userProject.getCompletionDateTime() != null;
    }

}
