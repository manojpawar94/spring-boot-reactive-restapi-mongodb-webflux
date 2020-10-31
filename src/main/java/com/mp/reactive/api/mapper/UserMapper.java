package com.mp.reactive.api.mapper;

import org.springframework.stereotype.Component;

import com.mp.reactive.api.models.User;
import com.mp.reactive.api.vo.UserVo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * User object mapper, to have code reusability
 * 
 * @author manojpawar
 *
 */
@Component
public class UserMapper implements Mapper<User, UserVo> {

	@Override
	public Mono<UserVo> modelToView(Mono<User> m) {
		return m.map(u -> new UserVo(u.getUserId(), u.getFirstName(), u.getMiddleName(), u.getLastName(),
				u.getUsername(), u.getPassword()));
	}

	@Override
	public Mono<User> viewToModel(Mono<UserVo> v) {
		return v.map(u -> new User(u.getUserId(), u.getFirstName(), u.getMiddleName(), u.getLastName(), u.getUsername(),
				u.getPassword()));
	}

	@Override
	public Flux<UserVo> modelToViewMany(Flux<User> m) {
		return m.map(u -> new UserVo(u.getUserId(), u.getFirstName(), u.getMiddleName(), u.getLastName(),
				u.getUsername(), u.getPassword()));
	}

}
