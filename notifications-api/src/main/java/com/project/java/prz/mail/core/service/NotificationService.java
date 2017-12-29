package com.project.java.prz.mail.core.service;

import com.project.java.prz.common.core.dto.MailDTO;

/**
 * Created by Piotr on 24.06.2017.
 */
public interface NotificationService {

    void sendSimpleMail(MailDTO mailDTO);

}
