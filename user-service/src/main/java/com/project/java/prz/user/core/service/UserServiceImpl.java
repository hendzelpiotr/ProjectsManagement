package com.project.java.prz.user.core.service;

import com.project.java.prz.common.core.domain.security.RoleType;
import com.project.java.prz.common.core.domain.security.User;
import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.common.core.exception.UserException;
import com.project.java.prz.common.core.mapper.UserMapper;
import com.project.java.prz.user.core.repository.RoleRepository;
import com.project.java.prz.user.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.project.java.prz.common.core.exception.UserException.FailReason.USER_ALREADY_EXITS;

/**
 * Created by Piotr on 03.06.2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDTO registerNewUser(UserDTO userDTO) {
        User user = userRepository.findByLogin(userDTO.getLogin());

        if (user == null) {
            user = new User();
            user.setRole(roleRepository.findByName(RoleType.ROLE_STUDENT));
            user.setLogin(userDTO.getLogin());
            user.setPassword(encodePassword(userDTO.getPassword()));
            user.setEnabled(Boolean.FALSE);

            user = userRepository.save(user);

            return UserMapper.INSTANCE.convertToDTO(user);
        } else throw new UserException(USER_ALREADY_EXITS);
    }

    @Override
    public UserDTO enableUserAccount(Integer id, UserDTO userDTO) {
        User user = userRepository.findOne(id);

        if(!user.getEnabled()) {
           user.setEnabled(Boolean.TRUE);
           user = userRepository.save(user);
           return UserMapper.INSTANCE.convertToDTO(user);
        } else throw new UserException(UserException.FailReason.USER_ALREADY_ENABLED);
    }

    private String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}
