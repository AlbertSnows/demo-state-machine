package com.example.demostatemachine.model.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "role_in_movie")
public class role_in_movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "people_id", nullable = false)
	private person person;

	@ManyToOne
	@JoinColumn(name = "movie_id", nullable = false)
	private movie movie;

	@Column(nullable = false)
	private String role;

	public role_in_movie(Long id, com.example.demostatemachine.model.data.entities.person person, com.example.demostatemachine.model.data.entities.movie movie, String role) {
		this.id = id;
		this.person = person;
		this.movie = movie;
		this.role = role;
	}

	Long get_id() {
		return id;
	}

	person get_person() {
		return person;
	}

	movie get_movie() {
		return movie;
	}

	String get_role() { return role; }
}

