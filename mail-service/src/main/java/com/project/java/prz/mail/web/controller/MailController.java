package com.project.java.prz.mail.web.controller;

import com.project.java.prz.common.core.dto.MailDTO;
import com.project.java.prz.mail.core.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Piotr on 24.06.2017.
 */
@RestController
@RequestMapping("internal-api/mails")
public class MailController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity sendSimpleMail(@RequestBody MailDTO mailDTO) {
        notificationService.sendSimpleMail(mailDTO);
        return ResponseEntity.ok().build();
    }

}
