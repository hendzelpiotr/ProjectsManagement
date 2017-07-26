package com.project.java.prz.user.configuration.eureka;

import com.project.java.prz.user.core.client.EnabledAuthorizationServerClient;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by phendzel on 6/27/2017.
 */
@Configuration
public class FeignConfiguration {

    @Autowired
    private EnabledAuthorizationServerClient enabledAuthorizationServerClient;

    @Bean
    public RequestInterceptor getJwtRequestInterceptor() throws IOException {
        return new JwtRequestInterceptor();
    }

    private class JwtRequestInterceptor implements RequestInterceptor {
        @Override
        public void apply(RequestTemplate requestTemplate) {
            requestTemplate.header("Authorization", "Bearer " + "e085dff1-5208-4908-88af-d32348b53371");
            Map<String, String> parameter = new HashMap<>();
            parameter.put("","");
            enabledAuthorizationServerClient.postAccessToken(null, null);
        }
    }

}
