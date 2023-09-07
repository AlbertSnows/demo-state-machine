package com.example.demostatemachine.model.mutations;

import com.example.demostatemachine.model.data.repositories.H2.Movie;
import com.example.demostatemachine.model.data.repositories.H2.Person;
import com.example.demostatemachine.model.data.repositories.H2.RoleInMovie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("coreMutations")
public class Core {
	private static final Logger logger = LoggerFactory.getLogger(Core.class);
	private final Person person_repo;
	private final Movie movie_repo;
	private final RoleInMovie roleInMovie_repo;

	@Autowired
	public Core(Person person_repo_init, Movie movie_repo_init, RoleInMovie roleInMovie_repo_init) {
		this.person_repo = person_repo_init;
		this.movie_repo = movie_repo_init;
		this.roleInMovie_repo = roleInMovie_repo_init;
	}

	public void trySeedingDatabase() {
		logger.info("Ingesting csv's...");
		var entity_lists = com.example.demostatemachine.model.importing.Core.build_movie_database();
		logger.info("Prepping for seeding...");
		if(!(entity_lists.isValid())) {
			logger.error("Error trying to build database, no data could be imported.");
		} else {
			var entity_data = entity_lists.get();
			saveEntities(entity_data);
		}
	}

	public boolean checkAndSeedDatabase() {

		var myDataOptional = metarepo.findByKey("exampleKey");
		var seeded = true; // redis.get("seeded");
		if(!seeded) {
			coreMutations.trySeedingDatabase();
			// redis.put("seeded", true");
		}
		return true;
	}



	public void saveEntities(com.example.demostatemachine.model.importing.Core.EntityHolder entity_data) {
		logger.info("Adding people...");
		person_repo.saveAll(entity_data.people());
		logger.info("Adding movies...");
		movie_repo.saveAll(entity_data.movies());
		logger.info("Adding roles...");
		roleInMovie_repo.saveAll(entity_data.roles());
		logger.info("All data seeded!");
	}

}
