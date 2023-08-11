package com.example.demostatemachine.model.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Movie extends CrudRepository<com.example.demostatemachine.model.data.entities.Movie, Long>{

	List<com.example.demostatemachine.model.data.entities.Movie> findById(long id);
	List<com.example.demostatemachine.model.data.entities.Movie> findByTitle(String title);
	List<com.example.demostatemachine.model.data.entities.Movie> findByReleaseYear(int release_year);

}
