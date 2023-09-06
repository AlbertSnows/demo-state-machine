package com.example.demostatemachine.model.data.repositories;

import org.springframework.data.repository.CrudRepository;

public interface Meta extends CrudRepository<com.example.demostatemachine.model.data.entities.Meta, Long> {
	//	List<com.example.demostatemachine.model.data.entities.Movie> findById(long id);
	//	List<com.example.demostatemachine.model.data.entities.Movie> findByTitle(String title);
	//	List<com.example.demostatemachine.model.data.entities.Movie> findByReleaseYear(int release_year);
}
