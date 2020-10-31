package com.mp.reactive.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * Database Sequence Collection. It mentains sequence for each collection
 * 
 * @author manojpawar
 *
 */
@Data
@Document(collection = "database_sequences")
public class DatabaseSequence {

	@Id
	private String id;

	private long sequence;

}