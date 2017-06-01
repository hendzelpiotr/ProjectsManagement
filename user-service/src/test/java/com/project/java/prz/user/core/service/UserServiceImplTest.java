package com.project.java.prz.user.core.service;

import com.project.java.prz.common.configuration.mockito.MockitoExtension;
import com.project.java.prz.common.core.domain.security.Role;
import com.project.java.prz.common.core.domain.security.RoleType;
import com.project.java.prz.common.core.domain.security.User;
import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.user.core.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by Piotr on 30.05.2017.
 */
@Disabled
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final Integer USER_ID = 1;
    private static final Integer ROLE_ID = 11;
    private static final Integer LABORATORY_GROUP = 12;
    private static final String USER_LOGIN = "USER_login";
    private static final String NAME = "Adam";
    private static final String SURNAME = "Miking";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Test
    void shouldGetAllUsers() {
        when(userRepository.findAll()).thenReturn(dummyUsers());

        List<UserDTO> userDTOs = userService.getAll();

        assertNotNull(userDTOs);
        assertEquals(userDTOs.size(), dummyUsers().size());
        verify(userRepository, times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void shouldGetOneUserByLogin() {
        when(userRepository.findByLogin(USER_LOGIN)).thenReturn(dummyUser());

        UserDTO userDTO = userService.getOneByLogin(USER_LOGIN);

        assertNotNull(userDTO);
        assertEquals(userDTO.getId(), USER_ID);
        verify(userRepository, times(1)).findByLogin(USER_LOGIN);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void shouldGetOneUserById() {
        when(userRepository.findOne(USER_ID)).thenReturn(dummyUser());

        UserDTO userDTO = userService.getOne(USER_ID);

        assertNotNull(userDTO);
        assertEquals(userDTO.getId(), USER_ID);
        verify(userRepository, times(1)).findOne(USER_ID);
        verifyNoMoreInteractions(userRepository);
    }

    private User dummyUser() {
        User user = new User();
        user.setId(USER_ID);
        user.setEnabled(Boolean.TRUE);
        //user.setLaboratoryGroup(LABORATORY_GROUP);
        user.setLogin(USER_LOGIN);
        //user.setName(NAME);
        //user.setSurname(SURNAME);
        user.setRole(dummyRole());
        return user;
    }

    private List<User> dummyUsers() {
        return Collections.singletonList(dummyUser());
    }

    private Role dummyRole() {
        Role role = new Role();
        role.setId(ROLE_ID);
        role.setName(RoleType.ROLE_ADMIN);
        return role;
    }

}