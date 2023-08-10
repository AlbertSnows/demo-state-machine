package com.example.demostatemachine.model.data.repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface movie extends CrudRepository<com.example.demostatemachine.model.data.entities.movie, Long>{

	List<com.example.demostatemachine.model.data.entities.movie> find_by_id(long id);
	List<com.example.demostatemachine.model.data.entities.movie> find_by_title(String title);
	List<com.example.demostatemachine.model.data.entities.movie> find_by_year(int year);

}
