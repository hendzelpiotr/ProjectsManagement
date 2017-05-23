package com.project.java.prz.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Piotr on 20.05.2017.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.project.java.prz.oauth.repository")
@EnableTransactionManagement
@EntityScan(basePackages = "com.project.java.prz.common.core.domain")
public class AuthorizationServerRunner {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerRunner.class, args);
    }

}
