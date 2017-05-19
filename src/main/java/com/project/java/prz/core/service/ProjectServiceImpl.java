package com.project.java.prz.core.service;

import com.project.java.prz.core.domain.Project;
import com.project.java.prz.web.dto.ProjectDTO;
import com.project.java.prz.core.mapper.ProjectMapper;
import com.project.java.prz.core.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
