package com.example.demostatemachine.model.service;

import com.example.demostatemachine.model.data.entities.Meta;
import com.example.demostatemachine.model.service.redis.meta.MetaQueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("coreMutations")
public class Mutations {
	private static final Logger logger = LoggerFactory.getLogger(Mutations.class);

	private final MetaQueries metaQueries;

	@Qualifier("h2Mutations")
	private final com.example.demostatemachine.model.service.H2.Mutations h2Mutations;

	@Qualifier("metaMutations")
	private final com.example.demostatemachine.model.service.redis.meta.Mutations metaMutations;

	@Autowired
	public Mutations(
					MetaQueries metaQueries
					, com.example.demostatemachine.model.service.H2.Mutations h2Mutations
					, com.example.demostatemachine.model.service.redis.meta.Mutations metaMutations) {
		this.metaQueries = metaQueries;
		this.h2Mutations = h2Mutations;
		this.metaMutations = metaMutations;
	}

	@Async
	public void trySeedingDatabase() {
		logger.info("Ingesting csv's...");
		var entity_lists = com.example.demostatemachine.model.importing.Core.build_movie_database();
		logger.info("Prepping for seeding...");
		if(!(entity_lists.isValid())) {
			logger.error("Error trying to build database, no data could be imported.");
		} else {
			var entity_data = entity_lists.get();
			h2Mutations.saveEntities(entity_data);
		}
	}

	@Async
	public void checkAndSeedDatabase() {
		var thing =  metaQueries.get("seeded");
		if(!thing) {
			logger.info("Seeding db...");
			var foo = metaMutations.put(new Meta("seeded", true));
			var boo = metaMutations.put(new Meta("this-should-not-persist", true));
		} else {
			logger.info("Already seeded!");
		}

		var thing2 =  metaQueries.get("seeded");

//		var seeded = thing != null;
		if(false) {
			logger.info("Actually seeding db...");
			trySeedingDatabase();
		}
	}
}
