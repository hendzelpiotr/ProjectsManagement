package com.project.java.prz.user.core.client;

import com.project.java.prz.common.core.dto.MailDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by phendzel on 6/26/2017.
 */
@FeignClient("MAIL-SERVICE")
public interface MailServiceClient {

    @RequestMapping(value = "internal-api/mails", method = RequestMethod.POST)
    ResponseEntity sendSimpleMail(@RequestBody MailDTO mailDTO);

}
