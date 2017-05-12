package com.project.java.prz.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.annotation.MultipartConfig;

@SpringBootApplication(scanBasePackages = "com.project.java.prz")
@EnableJpaRepositories(basePackages = "com.project.java.prz.repository")
@EnableTransactionManagement
@EntityScan(basePackages = "com.project.java.prz.domain")
@MultipartConfig
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

}
