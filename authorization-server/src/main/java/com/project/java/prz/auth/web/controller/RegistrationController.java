package com.project.java.prz.auth.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.java.prz.auth.core.service.UserService;
import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.common.core.util.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by phendzel on 6/1/2017.
 */
@RestController
@RequestMapping("registrations")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping
    @JsonView(View.SecuredUser.class)
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO) {
        UserDTO registeredUser = userService.registerNewUser(userDTO);
        return ResponseEntity.status(201).body(registeredUser);
    }

}
