package com.project.java.prz.user.core.client;

import com.project.java.prz.common.core.client.NotificationsApiClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by phendzel on 6/26/2017.
 */
@FeignClient("NOTIFICATIONS-API")
public interface EnabledNotificationsApiClient extends NotificationsApiClient {
}
