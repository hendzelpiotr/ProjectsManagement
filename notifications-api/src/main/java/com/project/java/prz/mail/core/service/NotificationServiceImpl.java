package com.project.java.prz.mail.core.service;

import com.project.java.prz.common.core.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Created by Piotr on 24.06.2017.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger LOGGER = Logger.getLogger(NotificationServiceImpl.class.getName());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String receiver;

    @Override
    @Async
    public void sendSimpleMail(MailDTO mailDTO) {
        LOGGER.info("Sending simple mail.");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(receiver);
        mail.setTo(mailDTO.getEmailOfRecipient());
        mail.setSubject(mailDTO.getSubject());
        mail.setText(mailDTO.getBody());
        javaMailSender.send(mail);

        LOGGER.info("Email sent.");
    }

}
