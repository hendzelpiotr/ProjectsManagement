package com.project.java.prz.auth.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.java.prz.auth.core.service.UserService;
import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.common.core.exception.UserException;
import com.project.java.prz.common.core.util.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Piotr on 03.06.2017.
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("{id}")
    @JsonView(View.SecuredUser.class)
    public ResponseEntity<UserDTO> enableUser(Principal principal, @PathVariable("id") Integer id, @RequestBody @Valid UserDTO userDTO) {
        if(id.equals(userDTO.getId())) {
            principal.getName();
            UserDTO enabledUser = userService.enableUserAccount(id, userDTO);
            return ResponseEntity.ok(enabledUser);
        } else throw new UserException(UserException.FailReason.INVALID_IDS);
    }

}
