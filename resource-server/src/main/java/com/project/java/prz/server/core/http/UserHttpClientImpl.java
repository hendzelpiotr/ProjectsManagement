package com.project.java.prz.server.core.http;

import com.project.java.prz.common.core.domain.security.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by phendzel on 5/23/2017.
 */
@Component
public class UserHttpClientImpl implements UserHttpClient {

    @Value("${url.domain}")
    private String domain;

    @Value("${url.context}")
    private String context;

    @Override
    public User getOne(String login) {
        return httpClient().getForObject(getUrl() + login, User.class);
    }

    @Override
    public List<User> getAll() {
        return null;
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

    private String getUrl() {
        return domain + context;
    }

}
