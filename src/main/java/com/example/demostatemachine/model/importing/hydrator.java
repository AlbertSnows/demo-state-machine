package com.example.demostatemachine.model.importing;

import com.example.demostatemachine.model.data.repositories.movie;
import com.example.demostatemachine.model.data.repositories.person;
import com.example.demostatemachine.model.data.repositories.role_in_movie;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

public class hydrator implements CommandLineRunner {
	private final person person_repo;
	private final movie movie_repo;
	private final role_in_movie role_in_movie_repo;

	public hydrator(person person_repo_init, movie movie_repo_init, role_in_movie role_in_movie_repo_init) {
		this.person_repo = person_repo_init;
		this.movie_repo = movie_repo_init;
		this.role_in_movie_repo = role_in_movie_repo_init;
	}

	@Override
	public void run(String... args) throws Exception {
		var person1 = new com.example.demostatemachine.model.data.entities.person();
		person_repo.saveAll(List.of(person1, person2));
	}
}
