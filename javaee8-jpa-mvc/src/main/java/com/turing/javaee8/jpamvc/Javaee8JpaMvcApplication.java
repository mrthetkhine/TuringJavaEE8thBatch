package com.turing.javaee8.jpamvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableResilientMethods
@SpringBootApplication
public class Javaee8JpaMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(Javaee8JpaMvcApplication.class, args);
	}

}
