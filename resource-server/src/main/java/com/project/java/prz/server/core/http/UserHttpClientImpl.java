package com.project.java.prz.server.core.http;

import com.project.java.prz.common.core.domain.security.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
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
        User[] response = httpClient().getForObject(getUrl(), User[].class);
        return Arrays.asList(response);
    }

    @Override
    public User post(User user) {
        return httpClient().postForObject(getUrl(), user, User.class);
    }

    @Override
    public User put(User user, Integer id) {
        httpClient().put(getUrl(), user);
        return getOne(user.getLogin());
    }

    @Override
    public void delete(Integer id) {
        httpClient().delete(getUrl() + "" + id);
    }

    private RestTemplate httpClient() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restClient = builder.build();
        restClient.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request, body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });
        return restClient;
    }

    private String getUrl() {
        return domain + context;
    }

}
