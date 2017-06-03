package com.project.java.prz.auth.configuration.service;

import com.project.java.prz.auth.core.repository.UserRepository;
import com.project.java.prz.common.core.domain.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by Piotr on 21.05.2017.
 */
@Service
@Configuration
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName().name());
        return buildUserForAuthentication(user, authority);
    }

    private UserDetails buildUserForAuthentication(User user, GrantedAuthority authority) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.getEnabled(),
                true, true, true, Collections.singletonList(authority));
    }

}
