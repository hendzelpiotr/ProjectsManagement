package com.project.java.prz.server.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.project.java.prz")
@EnableJpaRepositories(basePackages = "com.project.java.prz.server.core.repository")
@EnableTransactionManagement
@EntityScan(basePackages = "com.project.java.prz.common.core.domain.general")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableEurekaClient
public class ProjectsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectsApiApplication.class, args);
    }

}
