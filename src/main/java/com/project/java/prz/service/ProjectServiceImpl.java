package com.project.java.prz.service;

import com.project.java.prz.dto.ProjectDTO;
import com.project.java.prz.mapper.ProjectMapper;
import com.project.java.prz.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Piotr on 06.04.2017.
 */
@Transactional
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<ProjectDTO> getAll() {
        return Arrays.asList(ProjectMapper.INSTANCE.convertToDto(projectRepository.findAll().get(0)),ProjectMapper.INSTANCE.convertToDto(projectRepository.findAll().get(1)));
    }

}
