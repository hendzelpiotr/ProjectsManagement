package com.project.java.prz.user.configuration;

import com.project.java.prz.common.configuration.swagger.SwaggerConfiguration;
import com.project.java.prz.contract.api.UserDetailApiClient;
import com.project.java.prz.user.core.client.EnabledNotificationsApiClient;
import com.project.java.prz.user.core.client.EnabledProjectsApiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.project.java.prz.user")
@EnableJpaRepositories(basePackages = "com.project.java.prz.user.core.repository")
@EnableTransactionManagement
@EntityScan(basePackages = "com.project.java.prz.common.core.domain.security")
@EnableEurekaClient
@EnableFeignClients(basePackageClasses = {EnabledNotificationsApiClient.class, EnabledProjectsApiClient.class, UserDetailApiClient.class})
@Import(SwaggerConfiguration.class)
public class UsersApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApiApplication.class, args);
    }

}
