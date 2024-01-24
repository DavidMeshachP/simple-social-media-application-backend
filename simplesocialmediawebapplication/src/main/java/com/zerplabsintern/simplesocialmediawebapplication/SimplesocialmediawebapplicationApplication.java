package com.zerplabsintern.simplesocialmediawebapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SimplesocialmediawebapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplesocialmediawebapplicationApplication.class, args);
	}

}
