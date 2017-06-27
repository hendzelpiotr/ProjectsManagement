package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.domain.general.Project;
import com.project.java.prz.common.core.dto.ProjectDTO;
import com.project.java.prz.common.core.exception.ProjectException;
import com.project.java.prz.common.core.mapper.ProjectMapper;
import com.project.java.prz.server.core.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by phendzel on 06.04.2017.
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<ProjectDTO> getAll() {
        List<Project> projects = projectRepository.findAll();
        return ProjectMapper.INSTANCE.convertToDTOs(projects);
    }

    @Override
    public ProjectDTO update(ProjectDTO projectDTO) {
        boolean projectExists = projectRepository.exists(projectDTO.getId());

        if (projectExists) {
            Project updatedProject = projectRepository.save(ProjectMapper.INSTANCE.convertToEntity(projectDTO));
            return ProjectMapper.INSTANCE.convertToDTO(updatedProject);
        } else throw new ProjectException(ProjectException.FailReason.PROJECT_DOES_NOT_EXIST);
    }

    @Override
    public ProjectDTO create(ProjectDTO projectDTO) {
        Project savedProject = projectRepository.save(ProjectMapper.INSTANCE.convertToEntity(projectDTO));
        return ProjectMapper.INSTANCE.convertToDTO(savedProject);
    }

    @Override
    public void delete(Integer id) {
        Project project = projectRepository.findOne(id);
        if (project != null) {
            if (CollectionUtils.isEmpty(project.getUserProjects())) {
                projectRepository.delete(id);
            } else throw new ProjectException(ProjectException.FailReason.PROJECT_CAN_NOT_BE_REMOVED);
        } else throw new ProjectException(ProjectException.FailReason.PROJECT_DOES_NOT_EXIST);
    }

}
