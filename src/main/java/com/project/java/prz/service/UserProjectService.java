package com.project.java.prz.service;

import com.project.java.prz.dto.UserProjectDTO;

import java.util.List;

/**
 * Created by Piotr on 17.04.2017.
 */
public interface UserProjectService {

    List<UserProjectDTO> getAll();
    UserProjectDTO assignProjectToStudent(String login, Integer projectId);
    void deleteById(String login, Integer id);

}
