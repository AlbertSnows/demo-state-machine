package com.example.demostatemachine.model.data.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.demostatemachine.model.data.entities.person;
import com.example.demostatemachine.model.data.entities.movie;

import java.util.List;

public interface role_in_movie extends CrudRepository<com.example.demostatemachine.model.data.entities.role_in_movie, Long>{

	List<com.example.demostatemachine.model.data.entities.role_in_movie> find_by_id(long id);
	List<com.example.demostatemachine.model.data.entities.role_in_movie> find_by_person(person person);
	List<com.example.demostatemachine.model.data.entities.role_in_movie> find_by_movie(movie movie);
	List<com.example.demostatemachine.model.data.entities.role_in_movie> find_by_role(String role);
}

