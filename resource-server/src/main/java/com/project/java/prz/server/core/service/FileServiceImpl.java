package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.domain.general.UserProject;
import com.project.java.prz.common.core.dto.UserDetailsDTO;
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

    @Value("${upload.destination.path}")
    private String destinationPath;

    @Override
    public void saveFile(byte[] fileAsByteArray, String extension, String login) throws IOException {
        UserDetailsDTO userDetailsDTO = getUserDetails(login);
        String directoryPath;

        directoryPath = createUserDirectoryPathAsString(userDetailsDTO);
        makeSureThatDirectoryExist(directoryPath);

        Path path = Paths.get(createFilePathAsString(extension, userDetailsDTO, directoryPath));
        Files.write(path, fileAsByteArray);

        UserProject userProject = userProjectRepository.findByUserLogin(userDetailsDTO.getLogin());
        updateSourceFileUploadedFlag(userProject);
    }

    @Override
    public byte[] readZipFile(String login) throws IOException {
        UserDetailsDTO userDetailsDTO = getUserDetails(login);

        String directoryPath = createUserDirectoryPathAsString(userDetailsDTO);
        makeSureThatDirectoryExist(directoryPath);

        Path path = Paths.get(createFilePathAsString("zip", userDetailsDTO, directoryPath));
        return Files.readAllBytes(path);
    }

    private UserDetailsDTO getUserDetails(String login) {
        return userService.getOne(login);
    }

    private void updateSourceFileUploadedFlag(UserProject userProject) {
        userProject.setSourceFilesUploaded(true);
        userProjectRepository.save(userProject);
    }

    private void makeSureThatDirectoryExist(String path) throws IOException {
        FileUtils.forceMkdir(new File(path));
    }

    private String createUserDirectoryPathAsString(UserDetailsDTO userDetailsDTO) {
        return destinationPath + userDetailsDTO.getLaboratoryGroup() + '\\' + userDetailsDTO.getName() + '_' + userDetailsDTO.getSurname() + '\\';
    }

    private String createFilePathAsString(String extension, UserDetailsDTO userDetailsDTO, String directoryPath) {
        return directoryPath + userDetailsDTO.getLogin() + "." + extension;
    }

}
