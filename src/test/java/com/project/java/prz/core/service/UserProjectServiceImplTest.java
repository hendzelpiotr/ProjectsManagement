package com.project.java.prz.core.service;

import com.project.java.prz.configuration.mockito.MockitoExtension;
import com.project.java.prz.core.domain.Project;
import com.project.java.prz.core.domain.User;
import com.project.java.prz.core.domain.UserProject;
import com.project.java.prz.core.repository.UserProjectRepository;
import com.project.java.prz.web.dto.UserProjectDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by phendzel on 5/19/2017.
 */
@ExtendWith(MockitoExtension.class)
public class UserProjectServiceImplTest {

    private final Integer USER_PROJECT_ID = 1;
    private final Integer USER_ID = 11;
    private final Integer PROJECT_ID = 22;
    private final LocalDateTime dateTimeOfProjectSelection = LocalDateTime.of(2017, 5, 13, 12, 15);

    @Mock
    private UserProjectRepository userProjectRepository;

    @Autowired
    @InjectMocks
    private UserProjectService userProjectService;

    @Test
    void getAll() throws Exception {
        List<UserProject> allUserProjects = getAllUserProjects();
        when(userProjectRepository.findAll()).thenReturn(allUserProjects);

        List<UserProjectDTO> userProjects = userProjectService.getAll();

        Assertions.assertNotNull(userProjects);
    }

    private List<UserProject> getAllUserProjects() {
        return Collections.singletonList(getUserProject());
    }

    private UserProject getUserProject() {
        User user = new User();
        user.setId(USER_ID);
        Project project = new Project();
        project.setId(PROJECT_ID);

        UserProject userProject = new UserProject();
        userProject.setId(USER_PROJECT_ID);
        userProject.setUser(user);
        userProject.setProject(project);
        userProject.setDatetimeOfProjectSelection(dateTimeOfProjectSelection);

        return userProject;
    }

}