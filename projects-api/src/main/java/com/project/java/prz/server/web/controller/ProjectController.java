package com.project.java.prz.server.web.controller;

import com.project.java.prz.common.core.dto.ProjectDTO;
import com.project.java.prz.common.core.exception.GeneralException;
import com.project.java.prz.server.core.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Piotr on 06.04.2017.
 */
@RestController
@RequestMapping("projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAll() {
        return ResponseEntity.ok(projectService.getAll());
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<ProjectDTO> create(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.create(projectDTO);
        return ResponseEntity.status(201).body(createdProject);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("{id}")
    public ResponseEntity<ProjectDTO> update(@PathVariable("id") Integer id, @RequestBody ProjectDTO projectDTO) {
        if (id.equals(projectDTO.getId())) {
            ProjectDTO updatedProject = projectService.update(projectDTO);
            return ResponseEntity.ok(updatedProject);
        } else throw new GeneralException(GeneralException.FailReason.INVALID_IDS);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
