package com.example.demostatemachine.model.data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Optional;

@RedisHash
public class Meta {

	@Id
	private String key;
	public Object value;

	// Constructors, getters, and setters

	public Meta(String key, Object value) {
		this.key = key;
		this.value = value;
	}
}
