package com.arago.adserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class AdServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdServerApplication.class, args);
	}
}
