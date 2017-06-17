package com.project.java.prz.user.core.dao;

import com.project.java.prz.common.core.domain.security.User;

import java.util.List;

/**
 * Created by Piotr on 05.06.2017.
 */
public interface UserDao {

    List<User> getDisabledAccounts();

}
