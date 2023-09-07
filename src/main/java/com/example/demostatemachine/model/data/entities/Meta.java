package com.example.demostatemachine.model.data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Optional;

@RedisHash
public class Meta {

	@Id
	private String id;
	private String key;
	private Object value;

	// Constructors, getters, and setters
}
