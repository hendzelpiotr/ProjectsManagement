package com.project.java.prz.server.core.http;

import com.project.java.prz.common.core.domain.security.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by phendzel on 5/23/2017.
 */
@Component
public class UserHttpClientImpl implements UserHttpClient {

    @Override
    public User getOne(String login) {
        return httpClient().getForObject("http://localhost:8083/internal-api/users/kdsadsa", User.class);
    }

    @Override
    public User post(User user) {
        return null;
    }

    @Override
    public User put(User user) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    private RestTemplate httpClient() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder.build();
    }

}
