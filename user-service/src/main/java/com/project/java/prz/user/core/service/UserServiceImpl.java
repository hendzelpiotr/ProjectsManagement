package com.project.java.prz.user.core.service;

import com.project.java.prz.common.core.domain.security.User;
import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.common.core.exception.UserException;
import com.project.java.prz.common.core.mapper.UserMapper;
import com.project.java.prz.user.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.convertToDTOs(users);
    }

    @Override
    public UserDTO getOneByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            return UserMapper.INSTANCE.convertToDTO(user);
        } else throw new UserException(UserException.FailReason.USER_NOT_FOUND);
    }

    @Override
    public UserDTO getOne(Integer id) {
        User user = userRepository.findOne(id);
        if (user != null) {
            return UserMapper.INSTANCE.convertToDTO(user);
        } else throw new UserException(UserException.FailReason.USER_NOT_FOUND);
    }

}
