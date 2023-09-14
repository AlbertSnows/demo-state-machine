package com.example.demostatemachine.model.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "role_in_movie")
public class RoleInMovie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "people_id", nullable = false)
	private Person person;

	@ManyToOne
	@JoinColumn(name = "movie_id", nullable = false)
	private Movie movie;

	@Column(nullable = false)
	private String role;

	public RoleInMovie() {}

	public RoleInMovie(Person person, Movie movie, String role) {
		this.person = person;
		this.movie = movie;
		this.role = role;
	}

	Long getId() {
		return id;
	}

	Person getPerson() {
		return person;
	}

	Movie getMovie() {
		return movie;
	}

	String getRole() { return role; }
}

