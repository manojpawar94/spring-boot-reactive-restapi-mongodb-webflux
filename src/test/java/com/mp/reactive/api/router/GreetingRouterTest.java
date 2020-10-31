package com.mp.reactive.api.router;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class GreetingRouterTest {

	private static final Logger logger = LoggerFactory.getLogger(GreetingRouterTest.class);

	@Autowired
	private ApplicationContext context;

	@Test
	public void testGreetingRoutes() {
		logger.info("starting API...");
		WebTestClient webTestClient = WebTestClient.bindToApplicationContext(context).build();
		webTestClient.get().uri("/hello").accept(MediaType.TEXT_PLAIN).exchange().expectStatus().isOk()
				.expectBody(String.class).value(response -> {
					logger.info("API Response: {}", response);
					assertEquals(response, "Hello WebFlux Reactive API");
				});
		logger.info("complete API call!");
	}

}
