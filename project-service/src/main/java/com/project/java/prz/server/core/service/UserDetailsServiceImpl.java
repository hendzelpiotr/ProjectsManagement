package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.domain.general.UserDetail;
import com.project.java.prz.common.core.dto.UserDetailDTO;
import com.project.java.prz.common.core.exception.UserDetailException;
import com.project.java.prz.common.core.mapper.UserDetailMapper;
import com.project.java.prz.server.core.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.project.java.prz.common.core.exception.UserDetailException.FailReason.USER_DETAIL_ALREADY_EXISTS;

/**
 * Created by phendzel on 5/24/2017.
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailRepository userDetailsRepository;

    @Override
    public UserDetailDTO getOne(String login) {
        return UserDetailMapper.INSTANCE.convertToDTO(userDetailsRepository.getOne(login));
    }

    @Override
    public UserDetailDTO createNew(UserDetailDTO userDetailsDTO) {
        UserDetail userDetail = userDetailsRepository.findOne(userDetailsDTO.getLogin());

        if (userDetail == null) {
            userDetail = new UserDetail();
            userDetail.setLogin(userDetailsDTO.getLogin());
            userDetail.setEmail(userDetailsDTO.getEmail());

            userDetail = userDetailsRepository.save(userDetail);
        } else throw new UserDetailException(USER_DETAIL_ALREADY_EXISTS);
        return UserDetailMapper.INSTANCE.convertToDTO(userDetail);
    }

    @Override
    public UserDetailDTO update(UserDetailDTO userDetailDTO) {
        UserDetail userDetail = userDetailsRepository.findOne(userDetailDTO.getLogin());

        if (userDetail != null) {
            userDetail.setName(userDetailDTO.getName());
            userDetail.setSurname(userDetailDTO.getSurname());
            userDetail.setLaboratoryGroup(userDetailDTO.getLaboratoryGroup());
            userDetail.setStudentNumber(userDetailDTO.getStudentNumber());

            UserDetail savedUserDetail = userDetailsRepository.save(userDetail);

            return UserDetailMapper.INSTANCE.convertToDTO(savedUserDetail);
        } else throw new UserDetailException(UserDetailException.FailReason.USER_DETAIL_DOES_NOT_EXIST);
    }

}
