package com.example.fittracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching



public class FitTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitTrackerApplication.class, args);
	}

}
