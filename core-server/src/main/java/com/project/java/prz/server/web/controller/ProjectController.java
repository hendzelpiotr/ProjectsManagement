package com.project.java.prz.server.web.controller;

import com.project.java.prz.common.core.dto.ProjectDTO;
import com.project.java.prz.server.core.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Piotr on 06.04.2017.
 */
@RestController
@RequestMapping("api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAll() {
        return ResponseEntity.ok(projectService.getAll());
    }

}
