package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.domain.general.Project;
import com.project.java.prz.common.core.domain.general.UserProject;
import com.project.java.prz.common.core.domain.security.RoleType;
import com.project.java.prz.common.core.dto.RoleDTO;
import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.common.core.dto.UserProjectDTO;
import com.project.java.prz.common.core.mapper.UserProjectMapper;
import com.project.java.prz.server.configuration.mockito.MockitoExtension;
import com.project.java.prz.server.core.repository.ProjectRepository;
import com.project.java.prz.server.core.repository.UserProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by phendzel on 5/26/2017.
 */
@ExtendWith(MockitoExtension.class)
class UserProjectServiceImplTest {

    private static final Integer USER_PROJECT_ID = 1;
    private static final Integer PROJECT_ID = 11;
    private static final Integer USER_ID = 111;
    private static final Integer ROLE_ID = 1111;
    private static final String USER_LOGIN = "user_login";
    private static final LocalDateTime DATETIME_OF_PROJECT_SELECTION = LocalDateTime.of(2016, 5, 12, 8, 0);
    private static final String PROJECT_NAME = "Web application";
    private static final Integer AVAILABLE_PROJECTS_COUNTER = 5;
    private static final String PROJECT_DESCRIPTION = "Dummy description";

    @Mock
    private Clock clock;

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
        verify(userProjectRepository, times(1)).findAll();
        verify(userService, times(userProjectDTOs.size())).getOne(anyInt());
        verifyNoMoreInteractions(userProjectRepository, userService);
    }

    @Test
    void shouldReturnUserProjectOfCurrentlyLoggedInUser() {
        when(userService.getOneByLogin(USER_LOGIN)).thenReturn(dummyUserDTO());
        when(userProjectRepository.findByUserId(USER_ID)).thenReturn(dummyUserProject());

        UserProjectDTO userProjectDTO = userProjectService.getUserProjectOfCurrentlyLoggedInUser(USER_LOGIN);

        assertNotNull(userProjectDTO);
        assertEquals(userProjectDTO.getId(), USER_PROJECT_ID);
        verify(userService, times(1)).getOneByLogin(USER_LOGIN);
        verify(userProjectRepository, times(1)).findByUserId(USER_ID);
        verifyNoMoreInteractions(userService, userProjectRepository);
    }

    @Test
    void shouldAssignProjectToStudent() {
        Project project = dummyProject();
        Project projectAfterSaved = dummyProject();
        projectAfterSaved.setAvailableProjectsCounter(AVAILABLE_PROJECTS_COUNTER - 1);

        when(userService.getOneByLogin(USER_LOGIN)).thenReturn(dummyUserDTO());
        when(projectRepository.getOne(PROJECT_ID)).thenReturn(project);
        when(userProjectRepository.findByUserId(USER_ID)).thenReturn(null);
        when(clock.instant()).thenReturn(DATETIME_OF_PROJECT_SELECTION.toInstant(ZoneOffset.UTC));
        when(clock.getZone()).thenReturn(ZoneOffset.UTC);
        when(userProjectRepository.save(any(UserProject.class))).thenReturn(dummyUserProject());
        when(projectRepository.save(project)).thenReturn(projectAfterSaved);

        UserProjectDTO userProjectDTO = userProjectService.assignProjectToStudent(USER_LOGIN, PROJECT_ID);

        assertNotNull(userProjectDTO);
        verify(userService, times(1)).getOneByLogin(USER_LOGIN);
        verify(projectRepository, times(1)).getOne(PROJECT_ID);
        verify(userProjectRepository, times(1)).findByUserId(USER_ID);
        verify(userProjectRepository, times(1)).save(any(UserProject.class));
        verify(projectRepository, times(1)).save(project);
        verifyNoMoreInteractions(userService, projectRepository, userProjectRepository);
    }

    @Test
    void shouldDeleteByIdUsingStudentAccount() {
        when(userService.getOneByLogin(USER_LOGIN)).thenReturn(getUserDTOWithSetRole(RoleType.ROLE_STUDENT));
        when(userProjectRepository.findByUserId(USER_ID)).thenReturn(dummyUserProject());
        when(clock.instant()).thenReturn(DATETIME_OF_PROJECT_SELECTION.toInstant(ZoneOffset.UTC));
        when(clock.getZone()).thenReturn(ZoneOffset.UTC);
        doNothing().when(userProjectRepository).delete(USER_PROJECT_ID);

        userProjectService.deleteById(USER_LOGIN, USER_PROJECT_ID);

        verify(userService, times(1)).getOneByLogin(USER_LOGIN);
        verify(userProjectRepository, times(1)).findByUserId(USER_ID);
        verify(userProjectRepository, times(1)).delete(USER_PROJECT_ID);
        verifyNoMoreInteractions(userService, userProjectRepository);
    }

    @Test
    void shouldUpdateUsingStudentAccount() {
        final String additionalInformation = "Temp additional information";
        UserProjectDTO dummyUserProjectDTOReadyToUpdate = dummyUserProjectDTO();
        dummyUserProjectDTOReadyToUpdate.setAdditionalInformation(additionalInformation);
        dummyUserProjectDTOReadyToUpdate.setMark("5.0");
        UserProject dummyUserProjectAfterUpdate = dummyUserProject();
        dummyUserProjectAfterUpdate.setAdditionalInformation(additionalInformation);

        when(userService.getOneByLogin(USER_LOGIN)).thenReturn(getUserDTOWithSetRole(RoleType.ROLE_STUDENT));
        when(userProjectRepository.findByUserId(USER_ID)).thenReturn(dummyUserProject());
        when(userProjectRepository.save(any(UserProject.class))).thenReturn(dummyUserProjectAfterUpdate);

        UserProjectDTO updatedUserProject = userProjectService.update(USER_LOGIN, dummyUserProjectDTOReadyToUpdate);

        assertNotNull(updatedUserProject);
        verify(userService, times(1)).getOneByLogin(USER_LOGIN);
        verify(userProjectRepository, times(1)).findByUserId(USER_ID);
        verify(userProjectRepository, times(1)).save(any(UserProject.class));
        verifyNoMoreInteractions(userService, userProjectRepository);
    }

    private UserProjectDTO dummyUserProjectDTO() {
        return UserProjectMapper.INSTANCE.convertToDTO(dummyUserProject());
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

    private RoleDTO dummyRoleDTO(RoleType roleType) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(ROLE_ID);
        roleDTO.setName(roleType);
        return roleDTO;
    }

    private UserDTO getUserDTOWithSetRole(RoleType roleType) {
        UserDTO userDTO = dummyUserDTO();
        userDTO.setRoleDTO(dummyRoleDTO(roleType));
        return userDTO;
    }

}