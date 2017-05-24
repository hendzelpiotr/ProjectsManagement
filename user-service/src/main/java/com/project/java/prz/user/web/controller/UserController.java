package com.project.java.prz.user.web.controller;

import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.user.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("logins/{login}")
    public ResponseEntity<UserDTO> getOneByLogin(@PathVariable("login") String login) {
        UserDTO userDTO = userService.getOneByLogin(login);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable("id") Integer id) {
        UserDTO userDTO = userService.getOne(id);
        return ResponseEntity.ok(userDTO);
    }

}
