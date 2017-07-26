package com.project.java.prz.user.core.client;

import com.project.java.prz.common.core.client.AuthorizationServerClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by phendzel on 7/26/2017.
 */
@FeignClient("AUTHORIZATION-SERVER")
public interface EnabledAuthorizationServerClient extends AuthorizationServerClient {
}
