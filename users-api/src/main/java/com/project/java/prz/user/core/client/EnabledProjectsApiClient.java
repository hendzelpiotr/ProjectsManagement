package com.project.java.prz.user.core.client;

import com.project.java.prz.common.core.client.ProjectsApiClient;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("PROJECTS-API")
public interface EnabledProjectsApiClient extends ProjectsApiClient {
}
