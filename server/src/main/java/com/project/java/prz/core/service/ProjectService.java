package com.project.java.prz.core.service;

import com.project.java.prz.core.dto.ProjectDTO;
import com.project.java.prz.core.dto.UserProjectDTO;

import java.util.List;

/**
 * Created by Piotr on 06.04.2017.
 */
public interface ProjectService {

    List<ProjectDTO> getAll();

}
