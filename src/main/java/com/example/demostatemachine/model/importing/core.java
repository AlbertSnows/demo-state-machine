package com.example.demostatemachine.model.importing;

import com.example.demostatemachine.utility.file;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.control.Try;

import java.nio.file.Path;

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
}
