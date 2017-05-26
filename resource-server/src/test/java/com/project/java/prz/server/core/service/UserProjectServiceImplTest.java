package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.domain.general.Project;
import com.project.java.prz.common.core.domain.general.UserProject;
import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.common.core.dto.UserProjectDTO;
import com.project.java.prz.server.configuration.mockito.MockitoExtension;
import com.project.java.prz.server.core.repository.ProjectRepository;
import com.project.java.prz.server.core.repository.UserProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by phendzel on 5/26/2017.
 */
@ExtendWith(value = MockitoExtension.class)
class UserProjectServiceImplTest {

    private static final Integer USER_PROJECT_ID = 1;
    private static final Integer PROJECT_ID = 11;
    private static final Integer USER_ID = 111;
    private static final String USER_LOGIN = "user_login";
    private static final LocalDateTime DATETIME_OF_PROJECT_SELECTION = LocalDateTime.of(2016, 5, 12, 8, 0);
    private static final String PROJECT_NAME = "Web application";
    private static final Integer AVAILABLE_PROJECTS_COUNTER = 5;
    private static final String PROJECT_DESCRIPTION = "Dummy description";

    @Mock
    private UserService userService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserProjectRepository userProjectRepository;

    @InjectMocks
    private UserProjectService userProjectService = new UserProjectServiceImpl();

    @Test
    void shouldReturnListOfUserProjects() {
        when(userProjectRepository.findAll()).thenReturn(dummyUserProjects());
        when(userService.getOne(USER_ID)).thenReturn(dummyUserDTO());

        List<UserProjectDTO> userProjectDTOs = userProjectService.getAll();

        assertNotNull(userProjectDTOs);
        assertEquals(userProjectDTOs.size(), dummyUserProjects().size());
    }

    @Test
    void shouldReturnUserProjectOfCurrentlyLoggedInUser() {
        when(userService.getOneByLogin(USER_LOGIN)).thenReturn(dummyUserDTO());
        when(userProjectRepository.findByUserId(USER_ID)).thenReturn(dummyUserProject());

        UserProjectDTO userProjectDTO = userProjectService.getUserProjectOfCurrentlyLoggedInUser(USER_LOGIN);

        assertNotNull(userProjectDTO);
        assertEquals(userProjectDTO.getId(), USER_PROJECT_ID);
    }

    @Test
    void assignProjectToStudent() {
        when(userService.getOneByLogin(USER_LOGIN)).thenReturn(dummyUserDTO());
        when(projectRepository.getOne(PROJECT_ID)).thenReturn(dummyProject());
        when(userProjectRepository.findByUserId(USER_ID));


    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }

    private UserProject dummyUserProject() {
        UserProject dummyUserProject = new UserProject();
        dummyUserProject.setId(USER_PROJECT_ID);
        dummyUserProject.setProject(dummyProject());
        dummyUserProject.setUserId(USER_ID);
        dummyUserProject.setDatetimeOfProjectSelection(DATETIME_OF_PROJECT_SELECTION);
        return dummyUserProject;
    }

    private Project dummyProject() {
        Project dummyProject = new Project();
        dummyProject.setId(PROJECT_ID);
        dummyProject.setName(PROJECT_NAME);
        dummyProject.setAvailableProjectsCounter(AVAILABLE_PROJECTS_COUNTER);
        dummyProject.setDescription(PROJECT_DESCRIPTION);
        return dummyProject;
    }

    private List<UserProject> dummyUserProjects() {
        return Collections.singletonList(dummyUserProject());
    }

    private UserDTO dummyUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(USER_ID);
        userDTO.setEnabled(TRUE);
        userDTO.setLogin(USER_LOGIN);
        return userDTO;
    }

}