package com.project.java.prz.core.service;

import com.project.java.prz.common.core.domain.User;
import com.project.java.prz.common.core.domain.UserProject;
import com.project.java.prz.server.core.repository.UserProjectRepository;
import com.project.java.prz.server.core.repository.UserRepository;
import com.project.java.prz.server.core.service.FileService;
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
    private UserRepository userRepository;

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Value("${upload.destination.path}")
    private String destinationPath;

    @Override
    public void saveFile(byte[] fileAsByteArray, String extension, String login) throws IOException {
        User user = getUser(login);
        String directoryPath;

        directoryPath = createUserDirectoryPathAsString(user);
        makeSureThatDirectoryExist(directoryPath);

        Path path = Paths.get(createFilePathAsString(extension, user, directoryPath));
        Files.write(path, fileAsByteArray);

        updateSourceFileUploadedFlag(user.getUserProject());
    }

    @Override
    public byte[] readZipFile(String login) throws IOException {
        User user = getUser(login);

        String directoryPath = createUserDirectoryPathAsString(user);
        makeSureThatDirectoryExist(directoryPath);

        Path path = Paths.get(createFilePathAsString("zip", user, directoryPath));
        return Files.readAllBytes(path);
    }

    private User getUser(String login) {
        return userRepository.findByLogin(login);
    }

    private void updateSourceFileUploadedFlag(UserProject userProject) {
        userProject.setSourceFilesUploaded(true);
        userProjectRepository.save(userProject);
    }

    private void makeSureThatDirectoryExist(String path) throws IOException {
        FileUtils.forceMkdir(new File(path));
    }

    private String createUserDirectoryPathAsString(User user) {
        return destinationPath + user.getLaboratoryGroup() + '\\' + user.getName() + '_' + user.getSurname() + '\\';
    }

    private String createFilePathAsString(String extension, User user, String directoryPath) {
        return directoryPath + user.getLogin() + "." + extension;
    }

}
