package com.project.java.prz.controller;

import com.project.java.prz.dto.UserProjectDTO;
import com.project.java.prz.service.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Piotr on 17.04.2017.
 */
@RestController
@RequestMapping("api/user-projects")
public class UserProjectController {

    @Autowired
    private UserProjectService userProjectService;

    @GetMapping
    public ResponseEntity<List<UserProjectDTO>> getAll() {
        List<UserProjectDTO> userProjectDTOs = userProjectService.getAll();
        return ResponseEntity.ok(userProjectDTOs);
    }

    @GetMapping("my")
    public ResponseEntity<UserProjectDTO> getUserProjectOfCurrentlyLoggedInUser(Principal principal) {
        UserProjectDTO userProjectDTO = userProjectService.getUserProjectOfCurrentlyLoggedInUser(principal.getName());
        return ResponseEntity.ok(userProjectDTO);
    }

    @PostMapping
    public ResponseEntity<UserProjectDTO> createNew(@RequestBody UserProjectDTO userProjectDTO, Principal principal) {
        UserProjectDTO createdUserProjectDTO = userProjectService.assignProjectToStudent(principal.getName(), userProjectDTO.getProjectDTO().getId());
        return ResponseEntity.ok(createdUserProjectDTO);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id, Principal principal) {
        userProjectService.deleteById(principal.getName(), id);
        return ResponseEntity.noContent().build();
    }

}
