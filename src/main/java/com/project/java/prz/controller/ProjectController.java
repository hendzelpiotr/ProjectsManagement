package com.project.java.prz.controller;

import com.project.java.prz.dto.ProjectDTO;
import com.project.java.prz.dto.UserProjectDTO;
import com.project.java.prz.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @Secured("ROLE_STUDENT")
    @PostMapping("{id}")
    public ResponseEntity<UserProjectDTO> assignProject(@PathVariable("id") Integer id, Principal principal) {
        return ResponseEntity.ok(projectService.assignProjectToStudent(principal.getName(), id));
    }

}
