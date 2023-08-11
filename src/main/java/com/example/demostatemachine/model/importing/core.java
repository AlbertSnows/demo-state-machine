package com.example.demostatemachine.model.importing;

import com.example.demostatemachine.model.data.entities.Movie;
import com.example.demostatemachine.model.data.entities.Person;
import com.example.demostatemachine.model.data.entities.RoleInMovie;
import com.example.demostatemachine.utility.file;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import org.apache.commons.lang3.math.NumberUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class core {
	public record EntityHolder(List<com.example.demostatemachine.model.data.entities.Person> people, List<com.example.demostatemachine.model.data.entities.Movie> movies, List<com.example.demostatemachine.model.data.entities.RoleInMovie> roles) {}

	private static final Logger logger = LoggerFactory.getLogger(com.example.demostatemachine.model.importing.core.class);

	private core() {
		throw new IllegalStateException("Utility class");
	}

	public static Try<List<String[]>> import_csv_data(Path csv_path) {
		return file.load_csv_file(csv_path);
	}

	public static HashMap<String, Try<List<String[]>>> import_csv_files(@NotNull HashMap<String, Path> csv_path_map) {
		return csv_path_map.mapValues(core::import_csv_data);
	}

	public static HashMap<String, Try<List<String[]>>> import_csv_map() {
		var actors_path = Paths.get("src/main/resources/data/actors_and_directors.csv");
		var movies_path = Paths.get("src/main/resources/data/movies.csv");
		var xbox_path = Paths.get("src/main/resources/data/xbox_feed.csv");
		return import_csv_files(HashMap.of(
						"people", actors_path,
						"movies", movies_path
//						,
//						"xbox", xbox_path
		));
	}

	public static @NotNull EntityHolder build_table_entities(@NotNull HashMap<String, List<String[]>> csv_data) {
		var movie_data = csv_data.get("movies");
		var movie_entities = List.ofAll(movie_data.get().tail().foldLeft(
						new ArrayList<Movie>(),
						(list, movie_row) -> {
							list.add(new Movie(Long.valueOf(movie_row[0]), movie_row[1], NumberUtils.toInt(movie_row[2], 0)));
							return list;
						}));
		var people_data = csv_data.get("people");
		var people_entities = List.ofAll(people_data.get().tail().foldLeft(
						new ArrayList<Person>(),
						(list, people_row) -> {
							list.add(new Person(people_row[1]));
							return  list;
						}));
		var name_to_entity = people_entities.foldLeft(
						HashMap.<String, Person>empty(),
						(map, person) -> map.put(person.getName(), person));
		var movie_id_to_entity = movie_entities.foldLeft(
						HashMap.<Long, Movie>empty(),
						(map, movie) -> map.put(movie.getId(), movie));
		var role_entities = List.ofAll(people_data.get().tail().foldLeft(
						new ArrayList<RoleInMovie>(),
						(list, people_row) -> {
							var person = name_to_entity.get(people_row[1]);
							var movie = movie_id_to_entity.get(Long.valueOf(people_row[0]));
							if(!movie.isEmpty()) {
								list.add(new RoleInMovie(person.get(), movie.get(), people_row[2]));
							}
							return list;
						}));
		return new EntityHolder(people_entities, movie_entities, role_entities);
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

	public static Validation<String, EntityHolder> build_movie_database() {
		var csv_data = load_in_csv_data();
		if(!(csv_data.isValid())) {
			return Validation.invalid("Problem loading in csv files");
		}
		var db_entities = build_table_entities(csv_data.get());
		return Validation.valid(db_entities);
	}
}
