package com.example.demostatemachine.model.data.repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Person extends CrudRepository<com.example.demostatemachine.model.data.entities.Person, Long>{

	List<com.example.demostatemachine.model.data.entities.Person> findById(long id);
	List<com.example.demostatemachine.model.data.entities.Person> findByName(String name);
}
