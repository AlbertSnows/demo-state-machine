package com.example.demostatemachine.model;


import com.example.demostatemachine.model.importing.validation;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.control.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Paths;

public class core {
	private static final Logger logger = LoggerFactory.getLogger(core.class);
	private core() {
		throw new IllegalStateException("Utility class");
	}
	public static Validation<String, HashMap<String, List<String[]>>> load_in_csv_data() {
		var actors_path = Paths.get("src/main/resources/data/actors_and_directors.csv");
		var movies_path = Paths.get("src/main/resources/data/movies.csv");
		var xbox_path = Paths.get("src/main/resources/data/xbox_feed.csv");
		var csv_data_map = com.example.demostatemachine.model.importing.core.import_csv_files(HashMap.of(
						"actors", actors_path,
						"movies", movies_path,
						"xbox", xbox_path));
		var validation_result = validation.validate_if_csv_files_were_read_correctly(csv_data_map);
		if(!(validation_result.isValid())) {
			var invalid_data = validation_result.getError();
			invalid_data.forEach(pair -> logger.error(pair._2()));
			return Validation.invalid("Error importing files. Refer to logs.");
		}
		return Validation.valid(validation_result.get());
	}

}
