package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.domain.general.UserProject;
import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.server.core.http.UserHttpClient;
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
    private UserHttpClient userHttpClient;

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Value("${upload.destination.path}")
    private String destinationPath;

    @Override
    public void saveFile(byte[] fileAsByteArray, String extension, String login) throws IOException {
        UserDTO userDTO = getUser(login);
        String directoryPath;

        directoryPath = createUserDirectoryPathAsString(userDTO);
        makeSureThatDirectoryExist(directoryPath);

        Path path = Paths.get(createFilePathAsString(extension, userDTO, directoryPath));
        Files.write(path, fileAsByteArray);

        UserProject userProject = userProjectRepository.findByUserId(userDTO.getId());
        updateSourceFileUploadedFlag(userProject);
    }

    @Override
    public byte[] readZipFile(String login) throws IOException {
        UserDTO userDTO = getUser(login);

        String directoryPath = createUserDirectoryPathAsString(userDTO);
        makeSureThatDirectoryExist(directoryPath);

        Path path = Paths.get(createFilePathAsString("zip", userDTO, directoryPath));
        return Files.readAllBytes(path);
    }

    private UserDTO getUser(String login) {
        return userHttpClient.getOne(login);
    }

    private void updateSourceFileUploadedFlag(UserProject userProject) {
        userProject.setSourceFilesUploaded(true);
        userProjectRepository.save(userProject);
    }

    private void makeSureThatDirectoryExist(String path) throws IOException {
        FileUtils.forceMkdir(new File(path));
    }

    private String createUserDirectoryPathAsString(UserDTO userDTO) {
        return destinationPath + userDTO.getLaboratoryGroup() + '\\' + userDTO.getName() + '_' + userDTO.getSurname() + '\\';
    }

    private String createFilePathAsString(String extension, UserDTO userDTO, String directoryPath) {
        return directoryPath + userDTO.getLogin() + "." + extension;
    }

}
