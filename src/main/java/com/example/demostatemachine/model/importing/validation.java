package com.example.demostatemachine.model.importing;

import io.vavr.Function1;
import io.vavr.collection.*;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import org.jetbrains.annotations.NotNull;

import java.nio.file.NoSuchFileException;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

public class validation {
	private validation() {
		throw new IllegalStateException("Utility class");
	}
	public static Validation<String, List<String[]>> check_if_successfully_read_csv(String key, Try<List<String[]>> csv_data) {
		return Validation.fromTry(csv_data).mapError(ex -> Match(ex).of(
		Case($(instanceOf(NoSuchFileException.class)), t -> "Could not find file at path for key: " + key),
		Case($(), "Uncaptured error. Cause: " + ex.getCause() + " | Message: " + ex.getMessage())));
	}

	public static Validation<HashMap<String, String>, HashMap<String, List<String[]>>>
	validate_if_csv_files_were_read_correctly(@NotNull HashMap<String, Try<List<String[]>>> unvalidated_csv_files) {
		HashMap<String, Validation<String, List<String[]>>> validated_csv_map = HashMap.empty();
		var validated_csv_files = unvalidated_csv_files.foldLeft(
						validated_csv_map,
						(validated_maps, pair) -> validated_maps.put(pair._1(), check_if_successfully_read_csv(pair._1(), pair._2())));
		var invalid_csv_files =
						validated_csv_files.filter(pair -> !(pair._2().isValid()));
		var all_csv_files_imported_successfully = invalid_csv_files.isEmpty();
		Function1<HashMap<String, Validation<String, List<String[]>>>, HashMap<String, List<String[]>>> get_valid_data =
						valid_csv_map -> {
			HashMap<String, List<String[]>> csv_data = HashMap.empty();
							return valid_csv_map.foldLeft(
							csv_data,
							(csv_map, valid_csv) -> csv_map.put(valid_csv._1(), valid_csv._2().get()));
		};
		Function1<HashMap<String, Validation<String, List<String[]>>>, HashMap<String, String>> get_invalid_data = csv_map -> {
			HashMap<String, String> error_messages = HashMap.empty();
			return invalid_csv_files.foldLeft(
							error_messages,
							(error_list, invalid_csv) -> error_list.put(invalid_csv._1(), invalid_csv._2().getError()));
		};
		return all_csv_files_imported_successfully
						? Validation.valid(get_valid_data.apply(validated_csv_files))
						: Validation.invalid(get_invalid_data.apply(validated_csv_files));
	}
}
