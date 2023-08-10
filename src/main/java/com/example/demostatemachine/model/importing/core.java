package com.example.demostatemachine.model.importing;

import com.example.demostatemachine.utility.file;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.control.Try;

import java.nio.file.Path;
import java.nio.file.Paths;

public class core {

	private core() {
		throw new IllegalStateException("Utility class");
	}

	public static Try<List<String[]>> import_csv_data(Path csv_path) {
		return file.load_csv_file(csv_path);
	}

	public static HashMap<String, Try<List<String[]>>> import_csv_files(HashMap<String, Path> csv_path_map) {
		return csv_path_map.mapValues(core::import_csv_data);
	}

	public static HashMap<String, Try<List<String[]>>> import_csv_map() {
		var actors_path = Paths.get("src/main/resources/data/actors_and_directors.csv");
		var movies_path = Paths.get("src/main/resources/data/movies.csv");
		var xbox_path = Paths.get("src/main/resources/data/xbox_feed.csv");
		return import_csv_files(HashMap.of(
						"actors", actors_path,
						"movies", movies_path,
						"xbox", xbox_path));
	}
}
