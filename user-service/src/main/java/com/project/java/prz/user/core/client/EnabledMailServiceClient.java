package com.project.java.prz.user.core.client;

import com.project.java.prz.common.core.client.MailServiceClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by phendzel on 6/26/2017.
 */
@FeignClient("MAIL-SERVICE")
public interface EnabledMailServiceClient extends MailServiceClient {

}
