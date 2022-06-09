package com.vip.coders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class VIPServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VIPServiceApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/validateUser");
				registry.addMapping("/createAccount");
				registry.addMapping("/availableMentors");
				registry.addMapping("/appliedMentors");
				registry.addMapping("/downloadResume");
				registry.addMapping("/approveMentor");
			}
		};
	}
}
