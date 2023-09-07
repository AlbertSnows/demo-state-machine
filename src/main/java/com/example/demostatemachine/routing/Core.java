package com.example.demostatemachine.routing;

import java.io.Serializable;
import java.util.Map;

import com.example.demostatemachine.model.data.repositories.H2.Movie;
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
	private final com.example.demostatemachine.model.mutations.Core coreMutations;

	@Autowired
	public Core(Movie movie_repo_init
              , com.example.demostatemachine.model.mutations.Core coreMutations) {
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

	@PostMapping("/database/seed")
	public ResponseEntity<Map<String, Serializable>> seedDatabase() {
		var seeded = false; // redis.get("seeded");
		if(!seeded) {
			coreMutations.trySeedingDatabase();
			// redis.put("seeded", true");
		}
		var success_message = seeded? "Already seeded, not doing it again." : "Request received, I'll begin seeding.";
		var http_code = seeded? 400 : 200;
		return ResponseEntity
						.status(http_code)
						.body(Map.of("message", success_message, "seeded", seeded));

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