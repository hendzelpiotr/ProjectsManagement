package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.domain.general.*;
import com.project.java.prz.common.core.dto.UserDetailDTO;
import com.project.java.prz.common.core.dto.UserProjectDTO;
import com.project.java.prz.common.core.exception.ProjectException;
import com.project.java.prz.common.core.exception.UserProjectException;
import com.project.java.prz.common.core.mapper.UserDetailMapper;
import com.project.java.prz.common.core.mapper.UserProjectMapper;
import com.project.java.prz.server.core.dao.UserSettingDao;
import com.project.java.prz.server.core.repository.ProjectRepository;
import com.project.java.prz.server.core.repository.UserProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Piotr on 17.04.2017.
 */
@Service
@Transactional
public class UserProjectServiceImpl implements UserProjectService {

    @Autowired
    private Clock clock;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Autowired
    private UserSettingDao userSettingDao;

    @Override
    public List<UserProjectDTO> getAll() {
        List<UserProject> userProjects = userProjectRepository.findAll();
        return UserProjectMapper.INSTANCE.convertToDTOs(userProjects);
    }

    @Override
    public UserProjectDTO getUserProjectOfCurrentlyLoggedInUser(String login) {
        UserDetailDTO userDetailsDTO = getUserDetails(login);
        UserProject userProject = userProjectRepository.findByUserDetailLogin(userDetailsDTO.getLogin());

        if (userProject != null) {
            UserProjectDTO userProjectDTO = UserProjectMapper.INSTANCE.convertToDTO(userProject);
            userProjectDTO.setUserDetailDTO(userDetailsDTO);
            return userProjectDTO;
        } else throw new UserProjectException(UserProjectException.FailReason.USER_PROJECT_NOT_FOUND);
    }

    @Override
    public UserProjectDTO assignProjectToStudent(String login, Integer projectId) {
        UserDetailDTO userDetailsDTO = getUserDetails(login);
        Project project = projectRepository.getOne(projectId);

        isExisting(project);

        Integer availableProjectsCounter = project.getAvailableProjectsCounter();

        UserProject userProject = userProjectRepository.findByUserDetailLogin(userDetailsDTO.getLogin());
        if (userProject == null
                && (availableProjectsCounter == null || availableProjectsCounter > 0)) {
            UserProject userProjectToSave = new UserProject();
            userProjectToSave.setProject(project);
            userProjectToSave.setUserDetail(UserDetailMapper.INSTANCE.convertToEntity(userDetailsDTO));
            userProjectToSave.setDateTimeOfProjectSelection(LocalDateTime.now(clock));
            userProjectToSave = userProjectRepository.save(userProjectToSave);

            if (availableProjectsCounter != null) {
                project.setAvailableProjectsCounter(availableProjectsCounter - 1);
                projectRepository.save(project);
            }

            return UserProjectMapper.INSTANCE.convertToDTO(userProjectToSave);
        } else {
            throw new UserProjectException(UserProjectException.FailReason.YOU_ALREADY_CHOSE_PROJECT);
        }

    }

    private LocalDateTime calculateScheduledCompletionDate() {
        LocalDateTime now = LocalDateTime.now(clock);
        int year = now.getYear();
        int month = now.getMonth().getValue();

        if (month >= 2 && month <= 6) {
            return LocalDateTime.of(year, 7, 10, 12, 0, 0);
        } else return LocalDateTime.of(year + 1, 2, 10, 12, 0, 0);

    }

    private void isExisting(Project project) {
        if (project == null)
            throw new ProjectException(ProjectException.FailReason.PROJECT_DOES_NOT_EXIST);
    }

    @Override
    public void deleteById(String login, Integer id) {
        UserDetailDTO userDetailsDTO = getUserDetails(login);

        if (isAdmin(userDetailsDTO) || isPossibleToRemove(id, userDetailsDTO)) {
            userProjectRepository.delete(id);
        } else throw new UserProjectException(UserProjectException.FailReason.YOU_CAN_NOT_ABANDON_PROJECT);
    }

    @Override
    public UserProjectDTO update(String login, UserProjectDTO userProjectDTO) {
        UserDetailDTO userDetailsDTO = getUserDetails(login);
        UserProject userProject;

        if (isAdmin(userDetailsDTO)) {
            userProject = prepareToUpdateByAdmin(userProjectDTO);
        } else {
            userProject = userProjectRepository.findByUserDetailLogin(userDetailsDTO.getLogin());
            if (userProject != null && userProject.getId().equals(userProjectDTO.getId())) {
                userProject = prepareToUpdateByStudent(userProjectDTO, userProject);
            } else throw new UserProjectException(UserProjectException.FailReason.YOU_CAN_NOT_UPDATE_USER_PROJECT);
        }

        UserProject updatedUserProject = userProjectRepository.save(userProject);
        return UserProjectMapper.INSTANCE.convertToDTO(updatedUserProject);
    }

    private UserProject prepareToUpdateByStudent(UserProjectDTO dto, UserProject dbUserProject) {
        if (!isAfterScheduledCompletionDateTime(getScheduledCompletionDate(dbUserProject))) {
            dbUserProject.setProgrammingLanguage(dto.getProgrammingLanguage());
            dbUserProject.setTechnologies(dto.getTechnologies());
            dbUserProject.setDatabase(dto.getDatabase());
            dbUserProject.setRepositoryLink(dto.getRepositoryLink());
            dbUserProject.setAdditionalInformation(dto.getAdditionalInformation());
            dbUserProject.setSourceFilesUploaded(dto.isSourceFilesUploaded());
            return dbUserProject;
        } else throw new UserProjectException(UserProjectException.FailReason.YOU_CAN_NOT_UPDATE_USER_PROJECT);
    }

    private LocalDate getScheduledCompletionDate(UserProject dbUserProject) {
        UserDetail userDetail = dbUserProject.getUserDetail();
        UserSetting foundUserSetting = userDetail.getUserSettings()
                .stream()
                .filter(userSetting -> (userSetting.getSetting().getName().getSettingName().equals(SettingName.SCHEDULED_COMPLETION_DATE.getSettingName())))
                .findFirst()
                .orElse(userSettingDao.getGlobalSetting(SettingName.SCHEDULED_COMPLETION_DATE));

        return LocalDate.parse(foundUserSetting.getValue());
    }

    private boolean isAfterScheduledCompletionDateTime(LocalDate date) {
        if (date == null) {
            return false;
        }
        return LocalDate.now(clock).isAfter(date);
    }

    private UserProject prepareToUpdateByAdmin(UserProjectDTO dto) {
        return UserProjectMapper.INSTANCE.convertToEntity(dto);
    }

    private boolean isAdmin(UserDetailDTO userDetailsDTO) {
        if (userDetailsDTO.getProfessorDTO() == null) {
            return true;
        } else return false;
    }

    private boolean isPossibleToRemove(Integer id, UserDetailDTO userDetailsDTO) {
        UserProject userProject = userProjectRepository.findByUserDetailLogin(userDetailsDTO.getLogin());

        return id.equals(userProject.getId())
                //TODO && !isAfterScheduledCompletionDateTime(userProject.getScheduledCompletionDateTime())
                && LocalDateTime.now(clock).isBefore(userProject.getDateTimeOfProjectSelection().plusDays(14));
    }

    private UserDetailDTO getUserDetails(String login) {
        return userService.getOne(login);
    }

}
