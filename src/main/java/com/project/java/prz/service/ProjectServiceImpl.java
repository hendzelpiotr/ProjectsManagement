package com.project.java.prz.service;

import com.project.java.prz.domain.Project;
import com.project.java.prz.domain.User;
import com.project.java.prz.domain.UserProject;
import com.project.java.prz.dto.ProjectDTO;
import com.project.java.prz.dto.UserProjectDTO;
import com.project.java.prz.exception.ProjectException;
import com.project.java.prz.mapper.ProjectMapper;
import com.project.java.prz.mapper.UserProjectMapper;
import com.project.java.prz.repository.ProjectRepository;
import com.project.java.prz.repository.UserProjectRepository;
import com.project.java.prz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by phendzel on 06.04.2017.
 */
@Transactional
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Override
    public List<ProjectDTO> getAll() {
        List<Project> projects = projectRepository.findAll();
        return ProjectMapper.INSTANCE.convertToDTOs(projects);
    }

    @Override
    public UserProjectDTO assignProjectToStudent(String login, Integer projectId) {
        User user = userRepository.findByLogin(login);
        Project project = projectRepository.getOne(projectId);

        if (project == null)
            throw new ProjectException(ProjectException.FailReason.PROJECT_DOES_NOT_EXIST);

        Integer availableProjectsCounter = project.getAvailableProjectsCounter();

        if (user.getUserProject() == null
                && (availableProjectsCounter == null || availableProjectsCounter > 0)) {
            UserProject userProject = new UserProject();
            userProject.setProject(project);
            userProject.setUser(user);
            userProject = userProjectRepository.save(userProject);

            if (availableProjectsCounter != null) {
                project.setAvailableProjectsCounter(availableProjectsCounter - 1);
                projectRepository.save(project);
            }

            return UserProjectMapper.INSTANCE.convertToDTO(userProject);
        } else {
            throw new ProjectException(ProjectException.FailReason.YOU_ALREADY_CHOSE_PROJECT);
        }
    }

}
