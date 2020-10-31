package com.mp.reactive.api.mapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Object Mapper Interface
 * 
 * @author manojpawar
 *
 * @param <M>
 *            Model Class
 * @param <V>
 *            View Class
 */
public interface Mapper<M, V> {

	Mono<V> modelToView(Mono<M> m);

	Mono<M> viewToModel(Mono<V> v);

	Flux<V> modelToViewMany(Flux<M> m);
}
