package com.mp.reactive.api.router;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.mp.reactive.api.models.User;
import com.mp.reactive.api.vo.UserVo;

@SpringBootTest
class UserRouterTest {

	private static final Logger logger = LoggerFactory.getLogger(UserRouterTest.class);

	@Autowired
	private ApplicationContext context;

	@Disabled("Enable while inserting the records")
	@Test
	void testCreateUser() {
		logger.info("starting API...");

		UserVo user = new UserVo();
		user.setFirstName("Rajesh");
		user.setLastName("Patil");
		user.setUsername("rajesh123`");
		user.setPassword("test@123");

		WebTestClient.bindToApplicationContext(context).build().post().uri("/users/save")
				.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(user)).exchange().expectStatus()
				.isCreated();
		logger.info("Completed API!");
	}

	@Disabled
	@Test
	void testFindAllUsers() {
		WebTestClient.bindToApplicationContext(context).build().get().uri("/users").exchange().expectStatus().isFound()
				.expectBodyList(User.class).value(response -> {
					logger.info("User: {}", response);
				}).hasSize(6);
	}

	@Disabled
	@Test
	void testFindUser() {
		UserVo user = new UserVo();
		user.setUserId(15);
		user.setFirstName("Sai");
		user.setLastName("Ram");
		user.setUsername("sairan");
		user.setPassword("test@123");

		WebTestClient.bindToApplicationContext(context).build().get().uri("/users/15").exchange().expectStatus()
				.isFound().expectBody(User.class).value(response -> {
					logger.info("User: {}", response);
					assertEquals(user.getFirstName(), response.getFirstName());
				});
	}

	@Test
	void testUpdateUser() {
		UserVo userVo = new UserVo();
		userVo.setUserId(15);
		userVo.setFirstName("Sai");
		// userVo.setMiddleName("");
		userVo.setLastName("Ram");
		userVo.setUsername("sairam12");
		userVo.setPassword("test@123");

		WebTestClient.bindToApplicationContext(context).build().put().uri("/users/update")
				.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(userVo)).exchange().expectStatus()
				.isOk().expectBody(User.class).value(response -> {
					logger.info("User: {}", response);
					assertEquals(userVo.getUsername(), response.getUsername());
				});
	}

}
