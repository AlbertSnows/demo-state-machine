package com.example.demostatemachine.model.importing;

import com.example.demostatemachine.model.data.repositories.Movie;
import com.example.demostatemachine.model.data.repositories.Person;
import com.example.demostatemachine.model.data.repositories.RoleInMovie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
public class hydrator implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(com.example.demostatemachine.model.importing.hydrator.class);

	@Override
	public void run(String... args) {
		logger.info("We're not running anything at startup anymore. To seed the database, hit /database/seed.");
	}
}
