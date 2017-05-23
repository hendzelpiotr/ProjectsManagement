package com.project.java.prz.server.core.http;

import com.project.java.prz.common.core.dto.UserDTO;

import java.util.List;

/**
 * Created by phendzel on 5/23/2017.
 */
public interface UserHttpClient {

    UserDTO getOne(String login);
    List<UserDTO> getAll();
    UserDTO post(UserDTO UserDTO);
    UserDTO put(UserDTO UserDTO, Integer id);
    void delete(Integer id);

}
