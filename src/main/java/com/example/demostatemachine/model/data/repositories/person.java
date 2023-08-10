package com.example.demostatemachine.model.data.repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface person extends CrudRepository<com.example.demostatemachine.model.data.entities.person, Long>{

	List<com.example.demostatemachine.model.data.entities.person> find_by_id(long id);
	List<com.example.demostatemachine.model.data.entities.person> find_by_name(String name);
}
