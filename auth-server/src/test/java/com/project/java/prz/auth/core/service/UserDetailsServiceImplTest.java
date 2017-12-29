package com.project.java.prz.auth.core.service;

import com.project.java.prz.auth.core.repository.UserRepository;
import com.project.java.prz.common.configuration.mockito.MockitoExtension;
import com.project.java.prz.common.core.domain.security.Role;
import com.project.java.prz.common.core.domain.security.RoleType;
import com.project.java.prz.common.core.domain.security.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Created by piotr on 7/10/17.
 */
@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    private static final Integer USER_ID = 1;
    private static final Integer ROLE_ID = 11;
    private static final String USER_LOGIN = "AdminPRZ";
    private static final String USER_PASSWORD = "zaq1@WSX";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsService userDetailsService = new UserDetailsServiceImpl();

    @Test
    @DisplayName("should return UserDetails")
    void shouldReturnUserDetails() {
        doReturn(dummyUser()).when(userRepository).findByLogin(USER_LOGIN);

        UserDetails userDetails = userDetailsService.loadUserByUsername(USER_LOGIN);

        assertNotNull(userDetails);
        assertEquals(USER_LOGIN, userDetails.getUsername());
    }

    @Test
    @DisplayName("should throw usernameNotFoundException")
    void shouldThrowUsernameNotFoundException() {
        doReturn(null).when(userRepository).findByLogin(USER_LOGIN);

        UsernameNotFoundException usernameNotFoundException = assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(USER_LOGIN));

        assertNotNull(usernameNotFoundException);
        assertEquals(usernameNotFoundException.getLocalizedMessage(), USER_LOGIN + " does not exist.");
    }

    @AfterEach
    void doAfterEachTest() {
        verify(userRepository, times(1)).findByLogin(USER_LOGIN);
        verifyNoMoreInteractions(userRepository);
    }

    private User dummyUser() {
        User user = new User();
        user.setId(USER_ID);
        user.setLogin(USER_LOGIN);
        user.setPassword(USER_PASSWORD);
        user.setEnabled(Boolean.TRUE);
        user.setRole(dummyRole());
        return user;
    }

    private Role dummyRole() {
        Role role = new Role();
        role.setId(ROLE_ID);
        role.setName(RoleType.ROLE_ADMIN);
        return role;
    }

}