package com.project.java.prz.common.core.client;

import com.project.java.prz.common.core.dto.MailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by phendzel on 6/27/2017.
 */
@RequestMapping("notifications-api")
public interface NotificationsApiClient {

    @RequestMapping(value = "mails", method = RequestMethod.POST)
    ResponseEntity sendSimpleMail(@RequestBody MailDTO mailDTO);

}
