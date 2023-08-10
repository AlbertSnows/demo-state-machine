package com.example.demostatemachine.routing;

import java.io.Serializable;
import java.util.Map;

import com.example.demostatemachine.model.data.repositories.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.HashMap;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

@SuppressWarnings("unused")
@Controller
public class core_controller {

	@Autowired
	private Movie movie_repo;

	@RequestMapping("/")
	public String default_route() {
		return "forward:/entry";
	}

	@RequestMapping("/entry")
	public ResponseEntity<Map<String, String>> entry () {
		return ResponseEntity.ok(Map.of("message", "API entry point! Go to /title to search for movies by title."));
	}

	@PostMapping("/greeting")
	public ResponseEntity<Map<String, String>> greeting(@RequestBody @NotNull Map<String, String> payload)
	{
		payload.put("response", "ok");
		return ResponseEntity.ok(payload);
	}

	@PostMapping("/title")
	public ResponseEntity<HashMap<String, Serializable>> get_movie_title(@RequestBody @NotNull Map<String, String> payload) {
		var title = payload.get("title");
		PolicyFactory policy = Sanitizers.FORMATTING;
		var safe_title = policy.sanitize(title);
		var movie = movie_repo.findByTitle(safe_title).stream().findFirst();
		if(movie.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		var mapper = new ObjectMapper();
		var movie_data = mapper.convertValue(movie.get(), Map.class);
		return ResponseEntity.ok(HashMap.of("title", safe_title, "year", movie.get().get_release_year()));
	}
}