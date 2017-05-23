package com.project.java.prz.user.controller;

import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Piotr on 25.03.2017.
 */
@RestController
@RequestMapping("internal-api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("{login}")
    public ResponseEntity<UserDTO> getOne(@PathVariable("login") String login) {
        return ResponseEntity.ok(userService.getOneByLogin(login));
    }

}
