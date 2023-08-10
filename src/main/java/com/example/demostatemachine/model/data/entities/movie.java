package com.example.demostatemachine.model.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "movie")
public class movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long id;

	@Column(nullable = false, unique = true)
	private final String title;

	@Column(nullable = false)
	private final int year;

	public movie(Long id, String title, int year) {
		this.id = id;
		this.title = title;
		this.year = year;
	}

	Long get_id() {
		return id;
	}

	String get_title() {
		return title;
	}

	int get_year() {
		return year;
	}
}
