package com.cloudshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudshareApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudshareApiApplication.class, args);
		System.out.println("Application Started...");
	}

}
