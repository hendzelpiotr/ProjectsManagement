package com.project.java.prz.user.core.service;

import com.project.java.prz.common.core.dto.UserDTO;

import java.util.List;

/**
 * Created by Piotr on 03.04.2017.
 */
public interface UserService {

    List<UserDTO> getAll();
    UserDTO getOneByLogin(String login);

}
