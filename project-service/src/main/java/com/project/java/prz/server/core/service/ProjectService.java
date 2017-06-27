package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.dto.ProjectDTO;

import java.util.List;

/**
 * Created by Piotr on 06.04.2017.
 */
public interface ProjectService {

    List<ProjectDTO> getAll();

    ProjectDTO update(ProjectDTO projectDTO);

    ProjectDTO create(ProjectDTO projectDTO);

    void delete(Integer id);
}
