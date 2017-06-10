package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.domain.general.UserDetail;
import com.project.java.prz.common.core.dto.UserDetailDTO;
import com.project.java.prz.common.core.exception.UserDetailsException;
import com.project.java.prz.common.core.mapper.UserDetailMapper;
import com.project.java.prz.server.core.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.project.java.prz.common.core.exception.UserDetailsException.FailReason.USER_DETAILS_ALREADY_EXISTS;

/**
 * Created by phendzel on 5/24/2017.
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetailDTO getOne(String login) {
        return UserDetailMapper.INSTANCE.convertToDTO(userDetailsRepository.getOne(login));
    }

    @Override
    public UserDetailDTO createNew(UserDetailDTO userDetailsDTO) {
        UserDetail userDetails = userDetailsRepository.findOne(userDetailsDTO.getLogin());

        if (userDetails == null) {
            userDetails = new UserDetail();
            userDetails.setLogin(userDetailsDTO.getLogin());

            userDetails = userDetailsRepository.save(userDetails);
        } else throw new UserDetailsException(USER_DETAILS_ALREADY_EXISTS);
        return UserDetailMapper.INSTANCE.convertToDTO(userDetails);
    }

}
