package com.project.java.prz.user.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.common.core.exception.UserException;
import com.project.java.prz.common.core.util.View;
import com.project.java.prz.user.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by Piotr on 05.06.2017.
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("disabled")
    @JsonView(View.SecuredUser.class)
    public ResponseEntity<List<UserDTO>> getDisabledAccounts() {
        List<UserDTO> disabledAccounts = userService.getAllDisabledAccounts();
        return ResponseEntity.ok(disabledAccounts);
    }

    @PutMapping("{id}/{login}")
    @JsonView(View.SecuredUser.class)
    public ResponseEntity<UserDTO> enableUser(Principal principal,
                                              @PathVariable("id") Integer id,
                                              @PathVariable("login") String login,
                                              @RequestBody @Valid UserDTO userDTO) {
        if (id.equals(userDTO.getId())) {
            principal.getName();
            UserDTO enabledUser = userService.enableUserAccount(id, login, userDTO);
            return ResponseEntity.ok(enabledUser);
        } else throw new UserException(UserException.FailReason.INVALID_IDS);
    }

}
