package com.project.java.prz.configuration.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by phendzel on 6/1/2017.
 */
@RestController
@RequestMapping("registration")
public class AuthorizationController {

    @PostMapping
    public ResponseEntity registerUser() {
        return ResponseEntity.ok().build();
    }

}
