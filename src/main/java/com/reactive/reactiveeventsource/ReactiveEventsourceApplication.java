package com.reactive.reactiveeventsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@SpringBootApplication
@EnableWebFlux
public class ReactiveEventsourceApplication implements WebFluxConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveEventsourceApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET")
				.allowedHeaders("*")
				.exposedHeaders("Access-Control-Allow-Origin",
						"Access-Control-Allow-Headers",
						"Access-Control-Max-Age",
						"Access-Control-Request-Headers",
						"Access-Control-Request-Method")
				.maxAge(3600);
	}
}
