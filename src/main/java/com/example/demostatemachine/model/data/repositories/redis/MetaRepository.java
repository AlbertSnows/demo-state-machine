package com.example.demostatemachine.model.data.repositories.redis;

import com.example.demostatemachine.model.data.entities.Meta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Refer to the meta service repos for more details on what this table is for
@Repository
public interface MetaRepository extends CrudRepository<Meta, String> {
	Optional<Meta> findByKey(String key);
}