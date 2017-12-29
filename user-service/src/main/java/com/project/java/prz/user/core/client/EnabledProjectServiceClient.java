package com.project.java.prz.user.core.client;

import com.project.java.prz.common.core.client.ProjectServiceClient;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("PROJECT-SERVICE")
public interface EnabledProjectServiceClient extends ProjectServiceClient {
}
