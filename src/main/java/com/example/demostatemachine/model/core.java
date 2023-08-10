package com.example.demostatemachine.model;


import com.example.demostatemachine.model.importing.validation;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.control.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class core {
	private static final Logger logger = LoggerFactory.getLogger(core.class);
	private core() {
		throw new IllegalStateException("Utility class");
	}
	public static Validation<String, HashMap<String, List<String[]>>> load_in_csv_data() {
		var csv_data_map = com.example.demostatemachine.model.importing.core.import_csv_map();
		var validation_result = validation.validate_if_csv_files_were_read_correctly(csv_data_map);
		if(!(validation_result.isValid())) {
			var invalid_data = validation_result.getError();
			invalid_data.forEach(pair -> logger.error(pair._2()));
			return Validation.invalid("Error importing files. Refer to logs.");
		}
		return Validation.valid(validation_result.get());
	}

	public static void build_movie_database() {
		var x = 1;
	}

}
