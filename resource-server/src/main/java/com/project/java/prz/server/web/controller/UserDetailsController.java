package com.project.java.prz.server.web.controller;

import com.project.java.prz.common.core.dto.UserDetailDTO;
import com.project.java.prz.common.core.exception.GeneralException;
import com.project.java.prz.server.core.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by Piotr on 04.06.2017.
 */
@RestController
@RequestMapping("api/user-details")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<UserDetailDTO> create(@RequestBody UserDetailDTO userDetailsDTO) {
        UserDetailDTO createdUserDetails = userDetailsService.createNew(userDetailsDTO);
        return ResponseEntity.status(201).body(createdUserDetails);
    }

    @PutMapping("me")
    public ResponseEntity<UserDetailDTO> updateInfoAboutCurrentlyLoggedInUser(@RequestBody UserDetailDTO userDetailDTO, Principal principal) {
        if(principal.getName().equals(userDetailDTO.getLogin())) {
            UserDetailDTO updatedUserDetailDTO = userDetailsService.update(userDetailDTO);
            return ResponseEntity.ok(updatedUserDetailDTO);
        } else throw new GeneralException(GeneralException.FailReason.INVALID_IDS);
    }

}
