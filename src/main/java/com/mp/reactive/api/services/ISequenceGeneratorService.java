package com.mp.reactive.api.services;

import java.util.concurrent.ExecutionException;

/**
 * Sequence Generator Service Interface
 * 
 * @author manojpawar
 *
 */
public interface ISequenceGeneratorService {

	/**
	 * It will read the sequence and update sequence from the Database Sequence
	 * collection for provided sequence name.
	 * 
	 * @param sequenceName
	 *            Name of the sequence
	 * @return return the sequence id for the sequenceName
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	long generateSequence(final String sequenceName) throws InterruptedException, ExecutionException;

}
