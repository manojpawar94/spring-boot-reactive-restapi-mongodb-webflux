package com.mp.reactive.api.services.impl;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mp.reactive.api.models.DatabaseSequence;
import com.mp.reactive.api.services.ISequenceGeneratorService;

/**
 * Implemention of ISequenceGeneratorService interface
 * 
 * @author manojpawar
 *
 */
@Service
public class SequenceGeneratorService implements ISequenceGeneratorService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	private ReactiveMongoOperations mongoOperations;

	@Autowired
	public SequenceGeneratorService(ReactiveMongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	@Override
	public long generateSequence(final String sequenceName) throws InterruptedException, ExecutionException {
		return mongoOperations.findAndModify(new Query(Criteria.where("_id").is(sequenceName)),
				new Update().inc("sequence", 1), DatabaseSequence.class).doOnSuccess(object -> {
					logger.debug("databaseSequence is evaluated: {}", object);
				}).toFuture().get().getSequence();
	}

}
