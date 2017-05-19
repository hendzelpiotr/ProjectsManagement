package com.project.java.prz.fileservice.core.service;

import java.io.IOException;

/**
 * Created by Piotr on 13.05.2017.
 */
public interface FileService {

    void saveFile(byte[] fileAsByteArray, String extension, String login) throws IOException;
    byte[] readZipFile(String login) throws IOException;

}
