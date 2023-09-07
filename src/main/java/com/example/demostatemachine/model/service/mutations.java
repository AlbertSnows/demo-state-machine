package com.example.demostatemachine.model.service;

import com.example.demostatemachine.model.service.redis.meta.Queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("coreMutations")
public class mutations {
	private static final Logger logger = LoggerFactory.getLogger(mutations.class);

	private final Queries metaQueries;

	@Autowired
	public mutations(Queries metaQueries) {
		this.metaQueries = metaQueries;
	}

	public void trySeedingDatabase() {
		logger.info("Ingesting csv's...");
		var entity_lists = com.example.demostatemachine.model.importing.Core.build_movie_database();
		logger.info("Prepping for seeding...");
		if(!(entity_lists.isValid())) {
			logger.error("Error trying to build database, no data could be imported.");
		} else {
			var entity_data = entity_lists.get();
			coreH2Mutations.saveEntities(entity_data);
		}
	}

	public boolean checkAndSeedDatabase() {
		var myDataOptional = metaQueries.findByKey("seeded");
		var seeded = true; // redis.get("seeded");
		if(!seeded) {
			trySeedingDatabase();
			// redis.put("seeded", true");
		}
		return true;
	}
}
