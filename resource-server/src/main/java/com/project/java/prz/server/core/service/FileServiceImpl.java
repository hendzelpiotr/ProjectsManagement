package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.domain.general.SettingName;
import com.project.java.prz.common.core.domain.general.UserProject;
import com.project.java.prz.common.core.dto.UserDetailDTO;
import com.project.java.prz.common.core.dto.UserSettingDTO;
import com.project.java.prz.common.core.exception.FileException;
import com.project.java.prz.server.core.repository.UserProjectRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static com.project.java.prz.common.core.exception.FileException.FailReason.YOU_CAN_NOT_SAVE_FILE;

/**
 * Created by Piotr on 13.05.2017.
 */
@Service
@Transactional
public class FileServiceImpl implements FileService {

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Autowired
    private UserSettingService userSettingService;

    @Value("${upload.destination.path}")
    private String destinationPath;

    @Override
    public void saveFile(byte[] fileAsByteArray, String extension, String login) throws IOException {
        UserDetailDTO userDetailsDTO = getUserDetails(login);
        String directoryPath;

        UserSettingDTO scheduledCompletionDateSetting = userSettingService.getUserSettingByNameAndLogin(userDetailsDTO.getLogin(), SettingName.SCHEDULED_COMPLETION_DATE);
        LocalDate scheduledCompletionDate = LocalDate.parse(scheduledCompletionDateSetting.getValue());

        if (!userSettingService.isAfterScheduledCompletionDateTime(scheduledCompletionDate)) {
            directoryPath = createUserDirectoryPathAsString(userDetailsDTO);
            makeSureThatDirectoryExist(directoryPath);

            Path path = Paths.get(createFilePathAsString(extension, userDetailsDTO, directoryPath));
            Files.write(path, fileAsByteArray);

            UserProject userProject = userProjectRepository.findByUserDetailLogin(userDetailsDTO.getLogin());
            updateSourceFileUploadedFlag(userProject);
        } else throw new FileException(YOU_CAN_NOT_SAVE_FILE);
    }

    @Override
    public byte[] readZipFile(String login) throws IOException {
        UserDetailDTO userDetailsDTO = getUserDetails(login);

        String directoryPath = createUserDirectoryPathAsString(userDetailsDTO);
        makeSureThatDirectoryExist(directoryPath);

        Path path = Paths.get(createFilePathAsString("zip", userDetailsDTO, directoryPath));
        return Files.readAllBytes(path);
    }

    private UserDetailDTO getUserDetails(String login) {
        return userService.getOne(login);
    }

    private void updateSourceFileUploadedFlag(UserProject userProject) {
        userProject.setSourceFilesUploaded(true);
        userProjectRepository.save(userProject);
    }

    private void makeSureThatDirectoryExist(String path) throws IOException {
        FileUtils.forceMkdir(new File(path));
    }

    private String createUserDirectoryPathAsString(UserDetailDTO userDetailsDTO) {
        return destinationPath + userDetailsDTO.getLaboratoryGroup() + '\\' + userDetailsDTO.getName() + '_' + userDetailsDTO.getSurname() + '\\';
    }

    private String createFilePathAsString(String extension, UserDetailDTO userDetailsDTO, String directoryPath) {
        return directoryPath + userDetailsDTO.getLogin() + "." + extension;
    }

}
