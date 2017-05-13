package com.project.java.prz.controller;

import com.project.java.prz.exception.GeneralException;
import com.project.java.prz.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

/**
 * Created by phendzel on 5/12/2017.
 */
@RestController
@Component
@RequestMapping("api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity fileUpload(@RequestParam("file") MultipartFile file, Principal principal) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            fileService.saveFile(file.getBytes(), FilenameUtils.getExtension(file.getOriginalFilename()),
                    principal.getName());
        } catch (IOException e) {
            throw new GeneralException(GeneralException.FailReason.IO_EXCEPTION);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<byte[]> getDownloadData() throws Exception {

        Path path = Paths.get("C:\\2\\Kamila_Gracik\\kgracik.zip");
        byte[] data = Files.readAllBytes(path);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("charset", "utf-8");
        responseHeaders.setContentType(MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE));
        responseHeaders.setContentLength(data.length);
        responseHeaders.set("Content-disposition", "attachment;");

        return new ResponseEntity<byte[]>(data, responseHeaders, HttpStatus.OK);
    }

}
