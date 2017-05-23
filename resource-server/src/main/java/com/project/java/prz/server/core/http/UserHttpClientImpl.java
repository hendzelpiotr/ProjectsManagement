package com.project.java.prz.server.core.http;

import com.project.java.prz.common.core.dto.UserDTO;
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
    public UserDTO getOne(String login) {
        return httpClient().getForObject(getUrl() + login, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAll() {
        UserDTO[] response = httpClient().getForObject(getUrl(), UserDTO[].class);
        return Arrays.asList(response);
    }

    @Override
    public UserDTO post(UserDTO UserDTO) {
        return httpClient().postForObject(getUrl(), UserDTO, UserDTO.class);
    }

    @Override
    public UserDTO put(UserDTO UserDTO, Integer id) {
        httpClient().put(getUrl(), UserDTO);
        return getOne(UserDTO.getLogin());
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
