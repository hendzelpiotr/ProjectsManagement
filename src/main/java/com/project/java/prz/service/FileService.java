package com.project.java.prz.service;

import java.io.IOException;

/**
 * Created by Piotr on 13.05.2017.
 */
public interface FileService {

    void saveFile(byte[] fileAsByteArray, String extension, String login) throws IOException;

}
