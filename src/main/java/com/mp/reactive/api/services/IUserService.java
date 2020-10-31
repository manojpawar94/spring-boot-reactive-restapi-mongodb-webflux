package com.mp.reactive.api.services;

import com.mp.reactive.api.models.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * User Service Interface
 * 
 * @author manojpawar
 *
 */
public interface IUserService {

	/**
	 * To create the new User document in Users collection
	 * 
	 * @param user
	 *            User Document Object
	 * @return return the created user doucment object with generated userId
	 */
	Mono<User> create(final User user);

	/**
	 * To find the User document in the Users collection by userId
	 * 
	 * @param userId
	 *            User Id of user to find
	 * @return returns the user if Mono<user> is found for userId otherwise empty
	 *         Mono<user>
	 */
	Mono<User> find(final long userId);

	/**
	 * To find all the user documents from the Users collection
	 * 
	 * @return It returns the users collection
	 */
	Flux<User> findAll();

	/**
	 * To update the User document from the User collection
	 * 
	 * @param mono
	 * @return
	 */
	Mono<User> update(final User mono);

	/**
	 * To delete the User document from User collection
	 * 
	 * @param userId
	 * @return
	 */
	Mono<Void> delete(final long userId);
}
