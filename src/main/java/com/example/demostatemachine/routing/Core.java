package com.example.demostatemachine.routing;

import java.io.Serializable;
import java.util.Map;

import com.example.demostatemachine.model.data.repositories.H2.Movie;
import com.example.demostatemachine.model.service.Mutations;
import com.example.demostatemachine.model.service.Queries;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

@SuppressWarnings("unused")
@Controller
public class Core {

	private final Movie movieRepo;

	@Qualifier("coreMutations")
	private final Mutations coreMutations;


	@Autowired
	public Core(Movie movie_repo_init
              , Mutations coreMutations) {
		this.movieRepo = movie_repo_init;
		this.coreMutations = coreMutations;
	}

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

	@RequestMapping("/database/seed")
	public ResponseEntity<Map<String, Serializable>> seedDatabase() {
		coreMutations.checkAndSeedDatabase();
		var success_message = "Request received. Handling seeding.";
		var http_code = 200;
		return ResponseEntity
						.status(http_code)
						.body(Map.of("message", success_message));

	}

	@PostMapping("/title")
	public ResponseEntity<Map<String, Serializable>> get_movie_title(@RequestBody @NotNull Map<String, String> payload) {
		var title = payload.get("title");
		PolicyFactory policy = Sanitizers.FORMATTING;
		var safe_title = policy.sanitize(title);
		var movies = movieRepo.findByTitle(safe_title);
		var movie = movies.stream().findFirst();
		if(movie.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Could not find movie with that title."));
		}
		var mapper = new ObjectMapper();
		var movie_data = mapper.convertValue(movie.get(), Map.class);
		return ResponseEntity.ok(Map.of("title", safe_title, "year", movie.get().getReleaseYear()));
	}
}