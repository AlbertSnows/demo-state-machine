package com.example.demostatemachine.model.data.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.demostatemachine.model.data.entities.Person;
import com.example.demostatemachine.model.data.entities.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleInMovie extends CrudRepository<com.example.demostatemachine.model.data.entities.RoleInMovie, Long>{

	List<com.example.demostatemachine.model.data.entities.RoleInMovie> findById(long id);
	List<com.example.demostatemachine.model.data.entities.RoleInMovie> findByPerson(Person person);
	List<com.example.demostatemachine.model.data.entities.RoleInMovie> findByMovie(Movie movie);
	List<com.example.demostatemachine.model.data.entities.RoleInMovie> findByRole(String role);
}

