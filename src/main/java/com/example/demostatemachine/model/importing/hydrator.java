package com.example.demostatemachine.model.importing;

import com.example.demostatemachine.model.data.repositories.Movie;
import com.example.demostatemachine.model.data.repositories.Person;
import com.example.demostatemachine.model.data.repositories.RoleInMovie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class hydrator implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(com.example.demostatemachine.model.importing.hydrator.class);
	private final Person person_repo;
	private final Movie movie_repo;
	private final RoleInMovie roleInMovie_repo;

	public hydrator(Person person_repo_init, Movie movie_repo_init, RoleInMovie roleInMovie_repo_init) {
		this.person_repo = person_repo_init;
		this.movie_repo = movie_repo_init;
		this.roleInMovie_repo = roleInMovie_repo_init;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Ingesting csv's...");
		var entity_lists = core.build_movie_database();
		logger.info("Prepping for seeding...");
		if(!(entity_lists.isValid())) {
			logger.error("Error trying to build database, no data could be imported.");
		} else {
			var entity_data = entity_lists.get();
			logger.info("Adding people...");
			person_repo.saveAll(entity_data.people());
			logger.info("Adding movies...");
			movie_repo.saveAll(entity_data.movies());
			logger.info("Adding roles...");
			roleInMovie_repo.saveAll(entity_data.roles());
			logger.info("All data seeded!");
		}
	}
}
