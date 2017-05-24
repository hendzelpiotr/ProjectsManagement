package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.server.core.http.UserHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by phendzel on 5/24/2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserHttpClient userHttpClient;

    @Override
    public UserDTO getOneByLogin(String login) {
        return userHttpClient.getOneByLogin(login);
    }

}
