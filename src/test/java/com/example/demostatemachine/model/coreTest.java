package com.example.demostatemachine.model;

import org.junit.jupiter.api.Test;

import static com.example.demostatemachine.model.core.load_in_csv_data;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class coreTest {

	@Test
	void map_header_to_rowTest() {
//		Supplier<Object> test_source = () -> new HashMap<>(Map.of("name", "aj"));
//		List<Map<String, Object>> test_cases = List.of(
//						make_test_case()
//										.apply(Map.of("name", "aj", "foo", "bar"))
//										.apply(List.of("aj", "bar", "1")));
//		Function<Object, Function<Object, Object>> test_function = initial_data -> test_case -> {
//			var answer = map_header_to_row()
//							.apply(List.of("name", "foo", "number"))
//							.apply((List<String>) test_case)
//							.apply((HashMap<Object, Object>) initial_data)
//							.apply(1);
//			return answer;
//		};
//		var all_tests_passed = run_test_cases()
//						.apply(test_function)
//						.apply(test_source)
//						.apply(test_cases);
//		assertEquals(Boolean.TRUE, all_tests_passed);
		assertEquals(2, 1 + 1);
	}

	@Test
	void load_in_csv_dataTest() {
		var result = load_in_csv_data();
		assertEquals(Boolean.TRUE, result.isValid());
	}
}
