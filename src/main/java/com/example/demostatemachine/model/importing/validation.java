package com.example.demostatemachine.model.importing;

import io.vavr.collection.List;
import io.vavr.control.Try;
import io.vavr.control.Validation;

public class validation {
	private validation() {
		throw new IllegalStateException("Utility class");
	}
	public static Validation<String, List<String[]>> validate_csv_data(String key, Try<List<String[]>> csv_data) {
		return Validation.fromTry(csv_data).mapError(ex -> "Error for key " + key + ": " + ex.getMessage());

//		if (csv_data.isSuccess()) {
//			return Validation.valid(csv_data.get());
//		} else {
//			return Validation.invalid("Error for key " + key + ": " + csv_data.getCause().getMessage());
//		}
	}

	public static void validate_csv_import() {
//		Map<String, Validation<String, Integer>> validatedMap = resultMap.map((key, tryValue) ->
//						validateTry(tryValue, key)
//		);
	}
}
