package com.mp.reactive.api.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mp.reactive.api.handlers.UserHandler;

/**
 * User Router to register the User routes
 * 
 * @author manojpawar
 *
 */
@Configuration
public class UserRouter {

	/**
	 * Register the User routes
	 * 
	 * @param userHandler
	 * @return
	 */
	@Bean
	public RouterFunction<ServerResponse> userRoutes(UserHandler userHandler) {
		return RouterFunctions
				.route(RequestPredicates.GET("/users").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						userHandler::findAllUsers)
				.andRoute(RequestPredicates.GET("/users/{userId}")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::findUser)
				.andRoute(
						RequestPredicates.POST("/users/save").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						userHandler::createUser)
				.andRoute(RequestPredicates.PUT("/users/update")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::updateUser)
				.andRoute(RequestPredicates.DELETE("/user/{userId}")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::deleteUser);
	}
}