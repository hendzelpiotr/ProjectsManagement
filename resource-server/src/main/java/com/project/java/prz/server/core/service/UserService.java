package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.dto.UserDTO;

/**
 * Created by phendzel on 5/24/2017.
 */
public interface UserService {

    UserDTO getOneByLogin(String login);
    UserDTO getOne(Integer id);

}
