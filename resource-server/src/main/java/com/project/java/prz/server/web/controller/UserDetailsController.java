package com.project.java.prz.server.web.controller;

import com.project.java.prz.common.core.dto.UserDetailsDTO;
import com.project.java.prz.server.core.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Piotr on 04.06.2017.
 */
@RestController
@RequestMapping("api/user-details")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<UserDetailsDTO> create(@RequestBody UserDetailsDTO userDetailsDTO) {
        UserDetailsDTO createdUserDetails = userDetailsService.createNew(userDetailsDTO);
        return ResponseEntity.status(201).body(createdUserDetails);
    }

}
