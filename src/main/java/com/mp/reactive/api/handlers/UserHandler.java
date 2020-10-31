package com.mp.reactive.api.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mp.reactive.api.mapper.Mapper;
import com.mp.reactive.api.models.User;
import com.mp.reactive.api.services.IUserService;
import com.mp.reactive.api.vo.UserVo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * UserHandler
 * 
 * @author manojpawar
 *
 */
@Component
public class UserHandler {

	@Autowired
	private IUserService userService;

	@Autowired
	private Mapper<User, UserVo> userMapper;

	static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

	/**
	 * To create new user
	 * 
	 * @param request
	 *            Http request object
	 * @return Http response object
	 */
	public Mono<ServerResponse> createUser(ServerRequest request) {
		Mono<User> newUser = userMapper.viewToModel(request.bodyToMono(UserVo.class)).flatMap(userService::create);
		return newUser.flatMap(user -> ServerResponse.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
				.body(String.format("New user is created with user id %d", user.getUserId()), String.class));
	}

	/**
	 * To find the user base
	 * 
	 * @param request
	 * @return
	 */

	public Mono<ServerResponse> findUser(ServerRequest request) {
		String userId = request.pathVariable("userId");
		Mono<UserVo> matchedUser = userMapper.modelToView(userService.find(Long.parseLong(userId)));
		return matchedUser.flatMap(u -> ServerResponse.status(HttpStatus.FOUND).contentType(MediaType.APPLICATION_JSON)
				.body(matchedUser, UserVo.class)).switchIfEmpty(notFound);

	}

	/**
	 * To retrive all the users
	 * 
	 * @param request
	 *            Http request object
	 * @return Http response object
	 */
	public Mono<ServerResponse> findAllUsers(ServerRequest request) {
		Flux<UserVo> users = userMapper.modelToViewMany(userService.findAll());
		return ServerResponse.status(HttpStatus.FOUND).contentType(MediaType.APPLICATION_JSON).body(users, UserVo.class)
				.switchIfEmpty(notFound);
	}

	/**
	 * To update the existing user
	 * 
	 * @param request
	 *            Http request object
	 * @return Http response object
	 */
	public Mono<ServerResponse> updateUser(ServerRequest request) {
		Mono<UserVo> updatedUser = userMapper
				.modelToView(userMapper.viewToModel(request.bodyToMono(UserVo.class)).flatMap(userService::update));
		return updatedUser.flatMap(
				user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(updatedUser, User.class))
				.switchIfEmpty(notFound);
	}

	/**
	 * To delete the existing user
	 * 
	 * @param request
	 *            Http request object
	 * @return Http response object
	 */
	public Mono<ServerResponse> deleteUser(ServerRequest request) {
		String userId = request.pathVariable("userId");
		Mono<Void> deleteUser = userService.delete(Long.parseLong(userId));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(deleteUser, Void.class);
	}

}
