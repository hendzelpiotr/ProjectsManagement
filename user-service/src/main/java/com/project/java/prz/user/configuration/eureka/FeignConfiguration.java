package com.project.java.prz.user.configuration.eureka;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.io.IOException;

/**
 * Created by phendzel on 6/27/2017.
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public RequestInterceptor getJwtRequestInterceptor() throws IOException {
        return new JwtRequestInterceptor();
    }

    private class JwtRequestInterceptor implements RequestInterceptor {
        @Override
        public void apply(RequestTemplate requestTemplate) {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
            requestTemplate.header("Authorization", "Bearer " + details.getTokenValue());
        }
    }

}
