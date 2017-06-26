package com.project.java.prz.mail.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "com.project.java.prz.mail")
@EnableEurekaClient
@EnableAsync
public class MailServiceRunner {

	public static void main(String[] args) {
		SpringApplication.run(MailServiceRunner.class, args);
	}

}
