package com.mp.reactive.api.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mp.reactive.api.handlers.GreetingHandler;

/**
 * Greeting Router to register the routes
 * 
 * @author manojpawar
 *
 */
@Configuration
public class GreetingRouter {

	/**
	 * Register the Greeting routes
	 * 
	 * @param greetingHandler
	 * @return
	 */
	@Bean
	public RouterFunction<ServerResponse> greetingRoutes(GreetingHandler greetingHandler) {
		return RouterFunctions.route(
				RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
				greetingHandler::hello);
	}
}
