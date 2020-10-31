package com.mp.reactive.api.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mp.reactive.api.models.User;
import com.mp.reactive.api.repositories.UserRepository;
import com.mp.reactive.api.services.IUserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementation of IUserService interface
 * 
 * @author manojpawar
 *
 */
@Service
public class UserService implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public Mono<User> create(User user) {
		logger.debug("creating new user...{}", user.toString());
		return userRepository.save(user);
	}

	@Override
	public Mono<User> find(long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public Flux<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Mono<User> update(User user) {
		logger.debug("creating new user...{}", user.toString());
		return userRepository.findById(user.getUserId()).flatMap(existingUser -> userRepository.save(user));

	}

	@Override
	public Mono<Void> delete(long userId) {
		return userRepository.deleteById(userId);
	}

}
