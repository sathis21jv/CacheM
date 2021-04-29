package com.cachem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CacheMApplication {
	public static void main(String[] args) {
		SpringApplication.run(CacheMApplication.class, args);
	}
}
