package com.project.java.prz.core.service;

import com.project.java.prz.core.domain.User;
import com.project.java.prz.web.dto.UserDTO;
import com.project.java.prz.core.mapper.UserMapper;
import com.project.java.prz.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

/**
 * Created by Piotr on 03.04.2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO getOne(Integer id) {
        User user = userRepository.findOne(id);
        return UserMapper.INSTANCE.convertToDTO(user);
    }

    @Override
    public List<UserDTO> getAll() {
        return Collections.emptyList();
    }

}
