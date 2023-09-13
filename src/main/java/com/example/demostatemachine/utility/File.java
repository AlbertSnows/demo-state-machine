package com.example.demostatemachine.utility;

import java.nio.file.Files;
import java.nio.file.Path;

import com.opencsv.CSVReader;
import io.vavr.collection.List;
import io.vavr.control.Try;

public class File {
	private File() {
		throw new IllegalStateException("Utility class");
	}

	public static Try<List<String[]>> load_csv_file(Path filepath) {
		var possible_file_reader = Try.of(() -> Files.newBufferedReader(filepath));
		var possible_csv_reader =
						possible_file_reader.flatMap(file_reader -> Try.of(() -> new CSVReader(file_reader)));
		return possible_csv_reader.flatMap(csv_reader -> Try.of(() -> List.ofAll(csv_reader.readAll())));
	}
}
