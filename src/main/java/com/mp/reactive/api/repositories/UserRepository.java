package com.mp.reactive.api.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.mp.reactive.api.models.User;

/**
 * User Collection Repository
 * 
 * @author manojpawar
 *
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, Long> {

}
