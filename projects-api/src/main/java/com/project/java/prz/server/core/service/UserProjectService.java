package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.dto.UserProjectDTO;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Created by Piotr on 17.04.2017.
 */
public interface UserProjectService {

    List<UserProjectDTO> getAll();
    UserProjectDTO getUserProjectOfCurrentlyLoggedInUser(String login);
    UserProjectDTO assignProjectToStudent(String login, Integer projectId);
    void deleteById(String login, Collection<? extends GrantedAuthority> authorities, Integer id);
    UserProjectDTO update(String login, Collection<? extends GrantedAuthority> authorities, UserProjectDTO userProjectDTO);

}
