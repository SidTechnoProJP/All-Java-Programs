package com.evaluation.foodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class FoodAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodAppApplication.class, args);
	}

}
