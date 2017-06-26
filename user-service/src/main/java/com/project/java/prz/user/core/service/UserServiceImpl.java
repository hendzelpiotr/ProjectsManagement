package com.project.java.prz.user.core.service;

import com.project.java.prz.common.core.domain.security.RoleType;
import com.project.java.prz.common.core.domain.security.User;
import com.project.java.prz.common.core.dto.MailDTO;
import com.project.java.prz.common.core.dto.RegistrationDTO;
import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.common.core.dto.UserDetailDTO;
import com.project.java.prz.common.core.exception.UserException;
import com.project.java.prz.common.core.mapper.UserMapper;
import com.project.java.prz.user.core.client.MailServiceClient;
import com.project.java.prz.user.core.dao.UserDao;
import com.project.java.prz.user.core.repository.RoleRepository;
import com.project.java.prz.user.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    private UserDao userDao;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private HttpService httpService;

    @Autowired
    private MailServiceClient mailServiceClient;

    @Value("${resource-server.url}")
    private String resourceServerUrl;

    @Value("${resource-server.user-details.context-path}")
    private String userDetailsContextPath;

    @Override
    public UserDTO registerNewUser(RegistrationDTO registrationDTO) {
        User user = userRepository.findByLogin(registrationDTO.getLogin());

        if (user == null) {
            user = new User();
            user.setRole(roleRepository.findByName(RoleType.ROLE_STUDENT));
            user.setLogin(registrationDTO.getLogin());
            user.setPassword(encodePassword(registrationDTO.getPassword()));
            user.setEnabled(Boolean.FALSE);

            user = userRepository.save(user);

            return UserMapper.INSTANCE.convertToDTO(user);
        } else throw new UserException(USER_ALREADY_EXITS);
    }

    private String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    @Override
    public UserDTO enableUserAccount(Integer id, String login, UserDTO userDTO) {
        User user = userRepository.findOne(id);

        if (!user.getEnabled()) {
            user.setEnabled(Boolean.TRUE);
            user = userRepository.save(user);

            UserDetailDTO userDetailsDTO = new UserDetailDTO();
            userDetailsDTO.setLogin(user.getLogin());
            ResponseEntity responseEntity = httpService.sendPost(resourceServerUrl + userDetailsContextPath, userDetailsDTO);

            MailDTO mailDTO = new MailDTO();
            mailDTO.setEmailOfRecipient(user.getLogin());
            mailDTO.setBody("Twoje konto zostało aktywowane");
            mailDTO.setSubject("Aktywacja konta");
            mailServiceClient.sendSimpleMail(mailDTO);

            if (responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
                return UserMapper.INSTANCE.convertToDTO(user);
            } else throw new UserException(UserException.FailReason.CAN_NOT_CREATE_USER);

        } else throw new UserException(UserException.FailReason.USER_ALREADY_ENABLED);
    }

    @Override
    public List<UserDTO> getAllDisabledAccounts() {
        List<User> disabledAccounts = userDao.getDisabledAccounts();
        return UserMapper.INSTANCE.convertToDTOs(disabledAccounts);
    }

}
