/**
 * 
 */
package com.mp.reactive.api.services;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mp.reactive.api.models.User;

/**
 * @author manojpawar
 *
 */
@SpringBootTest
class IUserServiceTest {

	@Autowired
	private IUserService userService;

	/**
	 * Test method for
	 * {@link com.mp.reactive.api.services.IUserService#create(com.mp.reactive.api.models.User)}.
	 */
	@ParameterizedTest
	@MethodSource("inputUsers")
	void testCreate(User inputUser) {
		userService.create(inputUser);
	}

	/**
	 * Test method for {@link com.mp.reactive.api.services.IUserService#find(long)}.
	 */
	@ParameterizedTest
	@MethodSource("inputUsers")
	void testFind(User inputUser) {
		userService.find(inputUser.getUserId());
	}

	/**
	 * Test method for {@link com.mp.reactive.api.services.IUserService#findAll()}.
	 */
	@ParameterizedTest
	@MethodSource("inputUsers")
	void testFindAll(User inputUser) {
		userService.findAll();
	}

	/**
	 * Test method for
	 * {@link com.mp.reactive.api.services.IUserService#update(com.mp.reactive.api.models.User)}.
	 */
	@ParameterizedTest
	@MethodSource("inputUsers")
	void testUpdate(User inputUser) {
		userService.update(inputUser);
	}

	/**
	 * Test method for
	 * {@link com.mp.reactive.api.services.IUserService#delete(long)}.
	 */
	@ParameterizedTest
	@MethodSource("inputUsers")
	void testDelete(User inputUser) {
		userService.delete(inputUser.getUserId());
	}

	static Stream<Arguments> inputUsers() {
		return Stream.of(Arguments.of(new User(4, "Manoj", "Mitharam", "Pawar", "adcbde", "xyz123")));
	}

}
