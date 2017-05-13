package com.project.java.prz.service;

import com.project.java.prz.domain.Project;
import com.project.java.prz.dto.ProjectDTO;
import com.project.java.prz.mapper.ProjectMapper;
import com.project.java.prz.repository.ProjectRepository;
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
