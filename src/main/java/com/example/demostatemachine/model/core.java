package com.example.demostatemachine.model;


import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.control.Validation;

import java.nio.file.Paths;

public class core {
	private core() {
		throw new IllegalStateException("Utility class");
	}
	public static void load_in_csv_data() {
		var actors_path = Paths.get("src/main/resources/data/actors_and_directors.csv");
		var movies_path = Paths.get("src/main/resources/data/movies.csv");
		var xbox_path = Paths.get("src/main/resources/data/xbox.csv");
		var csv_data_map = com.example.demostatemachine.model.importing.core.import_csv_files(HashMap.of(
						"actors", actors_path,
						"movies", movies_path,
						"xbox", xbox_path));
//		Validation<String, HashMap<String, List<String[]>>> validate_csv_import(csv_data_map);

	}

}
