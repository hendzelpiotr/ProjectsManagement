package com.project.java.prz.user.configuration.eureka;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
            requestTemplate.header("Authorization", "Bearer " + "e085dff1-5208-4908-88af-d32348b53371");
        }
    }

}
