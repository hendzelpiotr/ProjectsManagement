package com.project.java.prz.core.service;

import com.project.java.prz.web.dto.UserDTO;

import java.util.List;

/**
 * Created by Piotr on 03.04.2017.
 */
public interface UserService {

    UserDTO getOne(Integer id);
    List<UserDTO> getAll();

}
