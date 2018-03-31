package com.project.java.prz.server.web.controller;

import com.project.java.prz.common.core.dto.UserDetailDTO;
import com.project.java.prz.common.core.exception.GeneralException;
import com.project.java.prz.server.core.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by Piotr on 04.06.2017.
 */
@RestController
@RequestMapping("user-details")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserDetailDTO> create(@RequestBody UserDetailDTO userDetailsDTO) {
        UserDetailDTO createdUserDetails = userDetailsService.createNew(userDetailsDTO);
        return ResponseEntity.status(201).body(createdUserDetails);
    }

    @PutMapping("me")
    @Secured("ROLE_STUDENT")
    public ResponseEntity<UserDetailDTO> updateInfoAboutCurrentlyLoggedInUser(@RequestBody UserDetailDTO userDetailDTO, Principal principal) {
        if(principal.getName().equals(userDetailDTO.getLogin())) {
            UserDetailDTO updatedUserDetailDTO = userDetailsService.update(userDetailDTO);
            return ResponseEntity.ok(updatedUserDetailDTO);
        } else throw new GeneralException(GeneralException.FailReason.INVALID_IDS);
    }

    @GetMapping("me")
    @Secured({"ROLE_STUDENT", "ROLE_ADMIN"})
    public ResponseEntity<UserDetailDTO> getCurrentlyLoggedInUserDetail(Principal principal) {
        UserDetailDTO userDetailDTO = userDetailsService.getOne(principal.getName());
        return ResponseEntity.ok(userDetailDTO);
    }

    @GetMapping
    @Secured({"ROLE_STUDENT", "ROLE_ADMIN"})
    public ResponseEntity<String> getHelloWorld() {
        return ResponseEntity.ok("Hello World");
    }

}
