package com.example.demostatemachine.model.mutations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Core {
	private static final Logger logger = LoggerFactory.getLogger(Core.class);
	private final com.example.demostatemachine.model.mutations.refactorme.Core coreWriteMutations;


	@Autowired
	public Core(com.example.demostatemachine.model.mutations.refactorme.Core coreWriteMutations) {
		this.coreWriteMutations = coreWriteMutations;
	}

	public void trySeedingDatabase() {
		logger.info("Ingesting csv's...");
		var entity_lists = com.example.demostatemachine.model.importing.Core.build_movie_database();
		logger.info("Prepping for seeding...");
		if(!(entity_lists.isValid())) {
			logger.error("Error trying to build database, no data could be imported.");
		} else {
			var entity_data = entity_lists.get();
			coreWriteMutations.saveEntities(entity_data);
		}
	}
}
