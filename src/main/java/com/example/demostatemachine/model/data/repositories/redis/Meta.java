package com.example.demostatemachine.model.data.repositories.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Refer to the meta service repos for more details on what this table is for
@Repository
public interface Meta extends CrudRepository<Object, String> {
}