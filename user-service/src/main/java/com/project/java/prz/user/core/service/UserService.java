package com.project.java.prz.user.core.service;

import com.project.java.prz.common.core.dto.UserDTO;

/**
 * Created by Piotr on 03.06.2017.
 */
public interface UserService {

    UserDTO registerNewUser(UserDTO userDTO);
    UserDTO enableUserAccount(Integer id, UserDTO userDTO);

}
