package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.dto.UserDetailsDTO;

/**
 * Created by phendzel on 5/24/2017.
 */
public interface UserDetailsService {

    UserDetailsDTO getOne(String login);

    UserDetailsDTO createNew(UserDetailsDTO userDetailsDTO);

}
