package com.project.java.prz.service;

import com.project.java.prz.dto.UserDTO;

import java.util.List;

/**
 * Created by Piotr on 03.04.2017.
 */
public interface UserService {

    UserDTO getOne(Integer id);
    List<UserDTO> getAll();

}
