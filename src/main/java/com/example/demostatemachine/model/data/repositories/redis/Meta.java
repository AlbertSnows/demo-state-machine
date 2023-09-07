package com.example.demostatemachine.model.data.repositories.redis;

import org.springframework.data.repository.CrudRepository;

// Refer to the meta service repos for more details on what this table is for
public interface Meta extends CrudRepository<Object, String> {
}