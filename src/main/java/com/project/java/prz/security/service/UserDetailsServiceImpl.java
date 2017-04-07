package com.project.java.prz.security.service;

import com.project.java.prz.domain.User;
import com.project.java.prz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by phendzel on 4/7/2017.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
        return buildUserForAuthentication(user, authority);
    }

    private UserDetails buildUserForAuthentication(User user, GrantedAuthority authority) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true,
                true, true, true, Collections.singletonList(authority));
    }

}
