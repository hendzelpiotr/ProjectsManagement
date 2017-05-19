package com.project.java.prz.web.controller;

import com.project.java.prz.web.exception.GeneralException;
import com.project.java.prz.core.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getDownloadData(Principal principal) throws Exception {

        byte[] data = fileService.readZipFile(principal.getName());

        return ResponseEntity
                .ok()
                .header("Filename", principal.getName() + ".zip")
                .body(data);
    }

}
