package com.project.java.prz.server.web.controller;

import com.project.java.prz.common.core.dto.UserProjectDTO;
import com.project.java.prz.common.core.exception.UserProjectException;
import com.project.java.prz.server.core.service.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
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
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<UserProjectDTO>> getAll() {
        List<UserProjectDTO> userProjectDTOs = userProjectService.getAll();
        return ResponseEntity.ok(userProjectDTOs);
    }

    @GetMapping("my")
    @Secured("ROLE_STUDENT")
    public ResponseEntity<UserProjectDTO> getUserProjectOfCurrentlyLoggedInUser(Principal principal) {
        UserProjectDTO userProjectDTO = userProjectService.getUserProjectOfCurrentlyLoggedInUser(principal.getName());
        return ResponseEntity.ok(userProjectDTO);
    }

    @PostMapping
    @Secured("ROLE_STUDENT")
    public ResponseEntity<UserProjectDTO> assignProjectToStudent(@RequestBody UserProjectDTO userProjectDTO, Principal principal) {
        UserProjectDTO createdUserProjectDTO = userProjectService.assignProjectToStudent(principal.getName(), userProjectDTO.getProjectDTO().getId());
        return ResponseEntity.ok(createdUserProjectDTO);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id, Principal principal, Authentication authentication) {
        userProjectService.deleteById(principal.getName(), authentication.getAuthorities(), id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProjectDTO> update(@PathVariable("id") Integer id,
                                                 @RequestBody UserProjectDTO userProjectDTO,
                                                 Principal principal,
                                                 Authentication authentication) {
        if (id.equals(userProjectDTO.getId())) {
            UserProjectDTO updatedUserProjectDTO = userProjectService.update(principal.getName(),
                    authentication.getAuthorities(),
                    userProjectDTO);
            return ResponseEntity.ok(updatedUserProjectDTO);
        } else throw new UserProjectException(UserProjectException.FailReason.INVALID_IDS);
    }

}
