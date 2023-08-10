package com.example.demostatemachine.model.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	private final Long id;

	@Column(nullable = false, unique = true)
	private final String title;

	@Column(nullable = false)
	private final int releaseYear;

	public Movie(Long id, String title, int releaseYear) {
		this.id = id;
		this.title = title;
		this.releaseYear = releaseYear;
	}

	public Long get_id() {
		return id;
	}

	String get_title() {
		return title;
	}

	public int get_release_year() {
		return releaseYear;
	}
}
