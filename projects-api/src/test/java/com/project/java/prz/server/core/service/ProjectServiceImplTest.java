package com.project.java.prz.server.core.service;

import com.project.java.prz.common.configuration.mockito.MockitoExtension;
import com.project.java.prz.common.core.domain.general.Project;
import com.project.java.prz.common.core.domain.general.UserProject;
import com.project.java.prz.common.core.dto.ProjectDTO;
import com.project.java.prz.common.core.exception.ProjectException;
import com.project.java.prz.common.core.mapper.ProjectMapper;
import com.project.java.prz.server.core.repository.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Created by piotr on 7/11/17.
 */
@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    private static final Integer PROJECT_ID = 1;
    private static final String PROJECT_NAME = "Project name";

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService = new ProjectServiceImpl();

    @Test
    void shouldGetAllProjects() {
        List<Project> projectsToReturn = dummyProjects();
        doReturn(projectsToReturn).when(projectRepository).findAll();

        List<ProjectDTO> projectsDTOs = projectService.getAll();

        assertNotNull(projectsDTOs);
        assertEquals(projectsToReturn.size(), projectsDTOs.size());
        verify(projectRepository, times(1)).findAll();
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    void shouldUpdateProject() {
        doReturn(true).when(projectRepository).exists(PROJECT_ID);
        doReturn(dummyProject()).when(projectRepository).save(any(Project.class));

        ProjectDTO updatedProjectDTO = projectService.update(dummyProjectDTO());

        assertNotNull(updatedProjectDTO);
        assertEquals(PROJECT_ID, updatedProjectDTO.getId());
        assertEquals(PROJECT_NAME, updatedProjectDTO.getName());
        verify(projectRepository, times(1)).exists(PROJECT_ID);
        verify(projectRepository, times(1)).save(any(Project.class));
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    @DisplayName("should throw ProjectException because of project can not be updated")
    void shouldThrowProjectExceptionDuringUpdating() {
        doReturn(false).when(projectRepository).exists(PROJECT_ID);

        ProjectException projectException = assertThrows(ProjectException.class, () -> projectService.update(dummyProjectDTO()));

        assertNotNull(projectException);
        assertEquals(ProjectException.FailReason.PROJECT_DOES_NOT_EXIST, projectException.getFailReason());
        verify(projectRepository, times(1)).exists(PROJECT_ID);
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    void shouldCreateNewProject() {
        doReturn(dummyProject()).when(projectRepository).save(any(Project.class));

        ProjectDTO createdProjectDTO = projectService.create(dummyProjectDTO());

        assertNotNull(createdProjectDTO);
        assertEquals(PROJECT_ID, createdProjectDTO.getId());
        assertEquals(PROJECT_NAME, createdProjectDTO.getName());
        verify(projectRepository, times(1)).save(any(Project.class));
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    void shouldRemoveProject() {
        doReturn(dummyProject()).when(projectRepository).findOne(PROJECT_ID);

        projectService.delete(PROJECT_ID);

        verify(projectRepository, times(1)).findOne(PROJECT_ID);
        verify(projectRepository, times(1)).delete(PROJECT_ID);
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    @DisplayName("should throw ProjectException because of project has been already used")
    void shouldThrowProjectExceptionBecauseProjectHasBeenAlreadyUsed() {
        Project projectToRemove = dummyProject();
        projectToRemove.setUserProjects(Collections.singletonList(new UserProject()));
        doReturn(projectToRemove).when(projectRepository).findOne(PROJECT_ID);

        ProjectException projectException = assertThrows(ProjectException.class, () -> projectService.delete(PROJECT_ID));

        assertNotNull(projectException);
        assertEquals(ProjectException.FailReason.PROJECT_CAN_NOT_BE_REMOVED, projectException.getFailReason());
        verify(projectRepository, times(1)).findOne(PROJECT_ID);
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    @DisplayName("should throw ProjectException because of project does not exist")
    void shouldThrowProjectExceptionBecauseProjectDoesNotExist() {
        doReturn(null).when(projectRepository).findOne(PROJECT_ID);

        ProjectException projectException = assertThrows(ProjectException.class, () -> projectService.delete(PROJECT_ID));

        assertNotNull(projectException);
        assertEquals(ProjectException.FailReason.PROJECT_DOES_NOT_EXIST, projectException.getFailReason());
        verify(projectRepository, times(1)).findOne(PROJECT_ID);
        verifyNoMoreInteractions(projectRepository);
    }

    private List<Project> dummyProjects() {
        return Collections.singletonList(dummyProject());
    }

    private Project dummyProject() {
        Project project = new Project();
        project.setId(PROJECT_ID);
        project.setName(PROJECT_NAME);
        return project;
    }

    private ProjectDTO dummyProjectDTO() {
        return ProjectMapper.INSTANCE.convertToDTO(dummyProject());
    }

}