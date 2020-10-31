package com.mp.reactive.api.events;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.mp.reactive.api.models.User;
import com.mp.reactive.api.services.ISequenceGeneratorService;
import com.mp.reactive.api.services.impl.UserService;

/**
 * The Mongo event listener to handle the primary id sequence
 * 
 * @author manojpawar
 *
 */
@Component
public class UserModelListener extends AbstractMongoEventListener<User> {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	private ISequenceGeneratorService sequenceGenerator;

	@Autowired
	public UserModelListener(ISequenceGeneratorService sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}

	/**
	 * Method is triggered on event of inserting a new document in collection
	 */
	@Override
	public void onBeforeConvert(BeforeConvertEvent<User> event) {
		try {
			event.getSource().setUserId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Error:{}", e.getMessage());
		}
	}

}
