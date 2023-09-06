package com.example.demostatemachine.model;

import org.junit.jupiter.api.Test;

import static com.example.demostatemachine.model.importing.Core.build_movie_database;
import static com.example.demostatemachine.model.importing.Core.load_in_csv_data;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class coreTest {

	@Test
	void build_movie_databaseTest() {
		var result = build_movie_database();
		assertEquals(2, 1 + 1);
	}

	@Test
	void load_in_csv_dataTest() {
		var result = load_in_csv_data();
		assertEquals(Boolean.TRUE, result.isValid());
	}
}
