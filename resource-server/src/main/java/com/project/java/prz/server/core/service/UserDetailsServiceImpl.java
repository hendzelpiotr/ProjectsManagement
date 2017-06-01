package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.dto.UserDetailsDTO;
import com.project.java.prz.common.core.mapper.UserDetailsMapper;
import com.project.java.prz.server.core.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by phendzel on 5/24/2017.
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetailsDTO getOne(String login) {
        return UserDetailsMapper.INSTANCE.convertToDTO(userDetailsRepository.getOne(login));
    }

}
