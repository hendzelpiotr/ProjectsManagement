package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.dto.UserDetailDTO;

/**
 * Created by phendzel on 5/24/2017.
 */
public interface UserDetailsService {

    UserDetailDTO getOne(String login);

    UserDetailDTO createNew(UserDetailDTO userDetailsDTO);

    UserDetailDTO update(UserDetailDTO userDetailDTO);

}
