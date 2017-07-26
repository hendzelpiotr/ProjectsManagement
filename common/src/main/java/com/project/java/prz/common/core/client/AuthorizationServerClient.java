package com.project.java.prz.common.core.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

/**
 * Created by phendzel on 7/26/2017.
 */
@RequestMapping("auth-server")
public interface AuthorizationServerClient {

    @RequestMapping(value = "oauth/token", method= RequestMethod.POST)
    ResponseEntity<?> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters);

}
