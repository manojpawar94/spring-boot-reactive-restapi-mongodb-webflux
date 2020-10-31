package com.mp.reactive.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * Reactive Mongo - WebFLux application
 * 
 * @author manojpawar
 *
 */
@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "com.mp.reactive.api.repositories")
public class ApiBuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBuilderApplication.class, args);
	}

}
