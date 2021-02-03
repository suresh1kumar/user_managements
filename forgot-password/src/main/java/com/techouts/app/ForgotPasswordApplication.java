package com.techouts.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication(scanBasePackages = { "com.techouts.app.config","com.techouts.app.controller","com.techouts.app.entity","com.techouts.app.repository","com.techouts.app.service","com.techouts.app.serviceImpl","com.techouts.app.utils" })
//@EntityScan(basePackages = { "com.techouts" })
public class ForgotPasswordApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForgotPasswordApplication.class, args);
	}

}
