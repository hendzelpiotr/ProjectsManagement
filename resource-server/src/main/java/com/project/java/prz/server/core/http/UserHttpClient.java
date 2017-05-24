package com.project.java.prz.server.core.http;

import com.project.java.prz.common.core.dto.UserDTO;

import java.util.List;

/**
 * Created by phendzel on 5/23/2017.
 */
public interface UserHttpClient {

    UserDTO getOneByLogin(String login);
    UserDTO getOne(Integer id);
    List<UserDTO> getAll();
}
