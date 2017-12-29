package com.project.java.prz.server.core.service;

import com.project.java.prz.common.configuration.mockito.MockitoExtension;
import com.project.java.prz.common.core.domain.general.*;
import com.project.java.prz.common.core.dto.SettingDTO;
import com.project.java.prz.common.core.dto.UserDetailDTO;
import com.project.java.prz.common.core.dto.UserProjectDTO;
import com.project.java.prz.common.core.dto.UserSettingDTO;
import com.project.java.prz.common.core.exception.UserProjectException;
import com.project.java.prz.common.core.mapper.UserDetailMapper;
import com.project.java.prz.common.core.mapper.UserProjectMapper;
import com.project.java.prz.common.core.mapper.UserSettingMapper;
import com.project.java.prz.server.core.repository.ProjectRepository;
import com.project.java.prz.server.core.repository.UserProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by phendzel on 5/26/2017.
 */
@ExtendWith(MockitoExtension.class)
class UserProjectServiceImplTest {

    private static final Integer USER_PROJECT_ID = 1;
    private static final Integer PROJECT_ID = 11;
    private static final Integer USER_SETTING_ID = 111;
    private static final Integer SETTING_ID = 1111;
    private static final String USER_LOGIN = "user_login";
    private static final String USER_NAME = "Piotr";
    private static final Integer LABORATORY_GROUP = 2;
    private static final LocalDateTime DATETIME_OF_PROJECT_SELECTION = LocalDateTime.of(2016, 5, 12, 8, 0);
    private static final String PROJECT_NAME = "Web application";
    private static final Integer AVAILABLE_PROJECTS_COUNTER = 5;
    private static final String PROJECT_DESCRIPTION = "Dummy description";
    private static final String SETTING_VALUE = "2017-07-10";

    @Mock
    private Clock clock;

    @Mock
    private UserDetailsServiceImpl userService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserProjectRepository userProjectRepository;

    @Mock
    private UserSettingService userSettingService;

    @InjectMocks
    private UserProjectService userProjectService = new UserProjectServiceImpl();

    @Test
    void shouldReturnListOfUserProjects() {
        when(userProjectRepository.findAll()).thenReturn(dummyUserProjects());
        when(userService.getOne(USER_LOGIN)).thenReturn(dummyUserDetailsDTO());

        List<UserProjectDTO> userProjectDTOs = userProjectService.getAll();

        assertNotNull(userProjectDTOs);
        assertEquals(userProjectDTOs.size(), dummyUserProjects().size());
        verify(userProjectRepository, times(1)).findAll();
        verifyNoMoreInteractions(userProjectRepository);
        verifyZeroInteractions(userService);
    }

    @Test
    void shouldReturnUserProjectOfCurrentlyLoggedInUser() {
        when(userService.getOne(USER_LOGIN)).thenReturn(dummyUserDetailsDTO());
        when(userProjectRepository.findByUserDetailLogin(USER_LOGIN)).thenReturn(dummyUserProject());

        UserProjectDTO userProjectDTO = userProjectService.getUserProjectOfCurrentlyLoggedInUser(USER_LOGIN);

        assertNotNull(userProjectDTO);
        assertEquals(userProjectDTO.getId(), USER_PROJECT_ID);
        verify(userService, times(1)).getOne(USER_LOGIN);
        verify(userProjectRepository, times(1)).findByUserDetailLogin(USER_LOGIN);
        verifyNoMoreInteractions(userService, userProjectRepository);
    }

    @Test
    void shouldThrowUserProjectNotFoundException() {
        when(userService.getOne(USER_LOGIN)).thenReturn(dummyUserDetailsDTO());
        when(userProjectRepository.findByUserDetailLogin(USER_LOGIN)).thenReturn(null);

        UserProjectException exception = assertThrows(UserProjectException.class, () -> userProjectService.getUserProjectOfCurrentlyLoggedInUser(USER_LOGIN));

        assertEquals(exception.getFailReason(), UserProjectException.FailReason.USER_PROJECT_NOT_FOUND);
    }

    @Test
    void shouldAssignProjectToStudent() {
        Project project = dummyProject();
        Project projectAfterSaved = dummyProject();
        projectAfterSaved.setAvailableProjectsCounter(AVAILABLE_PROJECTS_COUNTER - 1);

        when(userService.getOne(USER_LOGIN)).thenReturn(dummyUserDetailsDTO());
        when(projectRepository.getOne(PROJECT_ID)).thenReturn(project);
        when(userProjectRepository.findByUserDetailLogin(USER_LOGIN)).thenReturn(null);
        when(clock.instant()).thenReturn(DATETIME_OF_PROJECT_SELECTION.toInstant(ZoneOffset.UTC));
        when(clock.getZone()).thenReturn(ZoneOffset.UTC);
        when(userProjectRepository.save(any(UserProject.class))).thenReturn(dummyUserProject());
        when(projectRepository.save(project)).thenReturn(projectAfterSaved);

        UserProjectDTO userProjectDTO = userProjectService.assignProjectToStudent(USER_LOGIN, PROJECT_ID);

        assertNotNull(userProjectDTO);
        verify(userService, times(1)).getOne(USER_LOGIN);
        verify(projectRepository, times(1)).getOne(PROJECT_ID);
        verify(userProjectRepository, times(1)).findByUserDetailLogin(USER_LOGIN);
        verify(userProjectRepository, times(1)).save(any(UserProject.class));
        verify(projectRepository, times(1)).save(project);
        verifyNoMoreInteractions(userService, projectRepository, userProjectRepository);
    }

    @Test
    void shouldDeleteByIdUsingStudentAccount() {
        UserProject userProject = dummyUserProject();

        when(userService.getOne(USER_LOGIN)).thenReturn(dummyUserDetailsDTO());
        when(userProjectRepository.findByUserDetailLogin(USER_LOGIN)).thenReturn(userProject);
        when(clock.instant()).thenReturn(DATETIME_OF_PROJECT_SELECTION.toInstant(ZoneOffset.UTC));
        when(clock.getZone()).thenReturn(ZoneOffset.UTC);
        when(userSettingService.getUserSettingBySettingName(userProject.getUserDetail().getUserSettings(), SettingName.SCHEDULED_COMPLETION_DATE)).thenReturn(dummyUserSettingDTO());
        doNothing().when(userProjectRepository).delete(USER_PROJECT_ID);

        userProjectService.deleteById(USER_LOGIN, Collections.emptyList(), USER_PROJECT_ID);

        verify(userService, times(1)).getOne(USER_LOGIN);
        verify(userProjectRepository, times(1)).delete(USER_PROJECT_ID);
        verify(userProjectRepository, times(1)).findByUserDetailLogin(USER_LOGIN);
        verify(userSettingService, times(1)).getUserSettingBySettingName(userProject.getUserDetail().getUserSettings(), SettingName.SCHEDULED_COMPLETION_DATE);
        verify(userSettingService, times(1)).isAfterScheduledCompletionDateTime(LocalDate.parse(SETTING_VALUE));
        verifyNoMoreInteractions(userService, userProjectRepository, userSettingService);
    }

    @Test
    void shouldUpdateUsingStudentAccount() {
        final String additionalInformation = "Temp additional information";
        UserProjectDTO dummyUserProjectDTOReadyToUpdate = dummyUserProjectDTO();
        dummyUserProjectDTOReadyToUpdate.setAdditionalInformation(additionalInformation);
        dummyUserProjectDTOReadyToUpdate.setMark("5.0");
        UserProject dummyUserProjectAfterUpdate = dummyUserProject();
        dummyUserProjectAfterUpdate.setAdditionalInformation(additionalInformation);
        UserProject userProject = dummyUserProject();

        when(userService.getOne(USER_LOGIN)).thenReturn(dummyUserDetailsDTO());
        when(userProjectRepository.findByUserDetailLogin(USER_LOGIN)).thenReturn(userProject);
        when(userProjectRepository.save(any(UserProject.class))).thenReturn(dummyUserProjectAfterUpdate);
        when(clock.instant()).thenReturn(DATETIME_OF_PROJECT_SELECTION.toInstant(ZoneOffset.UTC));
        when(clock.getZone()).thenReturn(ZoneOffset.UTC);
        when(userSettingService.getUserSettingBySettingName(userProject.getUserDetail().getUserSettings(), SettingName.SCHEDULED_COMPLETION_DATE)).thenReturn(dummyUserSettingDTO());

        UserProjectDTO updatedUserProject = userProjectService.update(USER_LOGIN, Collections.emptyList(), dummyUserProjectDTOReadyToUpdate);

        assertNotNull(updatedUserProject);
        verify(userService, times(1)).getOne(USER_LOGIN);
        verify(userProjectRepository, times(1)).findByUserDetailLogin(USER_LOGIN);
        verify(userSettingService, times(1)).getUserSettingBySettingName(userProject.getUserDetail().getUserSettings(), SettingName.SCHEDULED_COMPLETION_DATE);        verify(userProjectRepository, times(1)).save(any(UserProject.class));
        verify(userSettingService, times(1)).isAfterScheduledCompletionDateTime(LocalDate.parse(SETTING_VALUE));
        verifyNoMoreInteractions(userService, userProjectRepository, userSettingService);
    }

    private UserProjectDTO dummyUserProjectDTO() {
        return UserProjectMapper.INSTANCE.convertToDTO(dummyUserProject());
    }

    private UserProject dummyUserProject() {
        UserProject dummyUserProject = new UserProject();
        dummyUserProject.setId(USER_PROJECT_ID);
        dummyUserProject.setProject(dummyProject());
        dummyUserProject.setUserDetail(dummyUserDetails());
        dummyUserProject.setDateTimeOfProjectSelection(DATETIME_OF_PROJECT_SELECTION);
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

    private UserDetailDTO dummyUserDetailsDTO() {
        UserDetailDTO userDetailsDTO = new UserDetailDTO();
        userDetailsDTO.setLogin(USER_LOGIN);
        userDetailsDTO.setLaboratoryGroup(LABORATORY_GROUP);
        userDetailsDTO.setName(USER_NAME);
        return userDetailsDTO;
    }

    private UserDetail dummyUserDetails() {
        UserDetail userDetail = UserDetailMapper.INSTANCE.convertToEntity(dummyUserDetailsDTO());
        userDetail.setUserSettings(dummyUserSettings());
        return userDetail;
    }

    private UserSettingDTO dummyUserSettingDTO() {
        UserSettingDTO userSettingDTO = new UserSettingDTO();
        userSettingDTO.setId(USER_SETTING_ID);
        userSettingDTO.setSettingDTO(dummySettingDTO());
        userSettingDTO.setValue(SETTING_VALUE);
        return userSettingDTO;
    }

    private SettingDTO dummySettingDTO() {
        SettingDTO settingDTO = new SettingDTO();
        settingDTO.setId(SETTING_ID);
        settingDTO.setName(SettingName.SCHEDULED_COMPLETION_DATE);
        return settingDTO;
    }

    private List<UserSetting> dummyUserSettings() {
        UserSetting userSetting = UserSettingMapper.INSTANCE.convertToEntity(dummyUserSettingDTO());
        return Collections.singletonList(userSetting);
    }

}