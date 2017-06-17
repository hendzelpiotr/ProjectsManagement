package com.project.java.prz.user.core.service;

import com.project.java.prz.common.core.dto.UserDetailDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Piotr on 04.06.2017.
 */
@Service
public class HttpServiceImpl implements HttpService {

    @Override
    public ResponseEntity sendPost(String url, Object body) {
        return httpClient().postForEntity(url, body, UserDetailDTO.class);
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

}
