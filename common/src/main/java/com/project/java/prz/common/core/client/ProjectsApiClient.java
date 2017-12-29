package com.project.java.prz.common.core.client;

import com.project.java.prz.common.core.dto.UserDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("projects-api")
public interface ProjectsApiClient {

    @PostMapping("user-details")
    ResponseEntity<UserDetailDTO> create(@RequestBody UserDetailDTO userDetailsDTO);

}
