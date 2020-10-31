package com.mp.reactive.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User View Object
 * 
 * @author manojpawar
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
	private long userId;
	private String firstName, MiddleName, LastName, username;
	@JsonIgnore
	private String password;
}
