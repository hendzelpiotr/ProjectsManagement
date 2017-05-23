package com.project.java.prz.server.core.http;

import com.project.java.prz.common.core.domain.security.User;

import java.util.List;

/**
 * Created by phendzel on 5/23/2017.
 */
public interface UserHttpClient {

    User getOne(String login);
    List<User> getAll();
    User post(User user);
    User put(User user);
    void delete(Integer id);

}
