package com.project.java.prz.mail.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.project.java.prz.mail")
public class MailServiceRunner {

	public static void main(String[] args) {
		SpringApplication.run(MailServiceRunner.class, args);
	}

}
