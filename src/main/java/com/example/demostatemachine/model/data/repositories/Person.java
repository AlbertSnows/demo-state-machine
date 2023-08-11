package com.example.demostatemachine.model.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Person extends CrudRepository<com.example.demostatemachine.model.data.entities.Person, Long>{

	List<com.example.demostatemachine.model.data.entities.Person> findById(long id);
	List<com.example.demostatemachine.model.data.entities.Person> findByName(String name);
}
