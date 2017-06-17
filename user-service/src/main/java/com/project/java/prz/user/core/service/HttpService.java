package com.project.java.prz.user.core.service;

import org.springframework.http.ResponseEntity;

/**
 * Created by Piotr on 04.06.2017.
 */
public interface HttpService {

    ResponseEntity sendPost(String url, Object body);

}
