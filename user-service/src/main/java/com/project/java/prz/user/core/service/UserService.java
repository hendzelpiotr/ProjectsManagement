package com.project.java.prz.user.core.service;

import com.project.java.prz.common.core.dto.RegistrationDTO;
import com.project.java.prz.common.core.dto.UserDTO;

import java.util.List;

/**
 * Created by Piotr on 03.06.2017.
 */
public interface UserService {

    UserDTO registerNewUser(RegistrationDTO registrationDTO);
    UserDTO enableUserAccount(Integer id, String login, UserDTO userDTO);
    List<UserDTO> getAllDisabledAccounts();

}
