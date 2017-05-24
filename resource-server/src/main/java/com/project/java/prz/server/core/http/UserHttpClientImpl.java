package com.project.java.prz.server.core.http;

import com.project.java.prz.common.core.dto.UserDTO;
import com.project.java.prz.server.core.http.handler.HttpClientExceptionHandler;
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
    public UserDTO getOneByLogin(String login) {
        return httpClient().getForObject(getUrl() + login, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAll() {
        UserDTO[] response = httpClient().getForObject(getUrl(), UserDTO[].class);
        return Arrays.asList(response);
    }

    private RestTemplate httpClient() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restClient = builder.build();
        restClient.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request, body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });
        restClient.setErrorHandler(new HttpClientExceptionHandler());
        return restClient;
    }

    private String getUrl() {
        return domain + context;
    }

}
