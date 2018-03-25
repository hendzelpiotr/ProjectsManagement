package com.project.java.prz.mail.configuration;

import com.project.java.prz.common.configuration.swagger.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "com.project.java.prz.mail")
@EnableEurekaClient
@EnableAsync
@Import(SwaggerConfiguration.class)
public class NotificationsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationsApiApplication.class, args);
	}

}
