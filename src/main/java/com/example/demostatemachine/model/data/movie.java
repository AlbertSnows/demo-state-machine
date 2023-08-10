package com.example.demostatemachine.model.data;

import jakarta.persistence.*;

@Entity
@Table(name = "movie")
public class movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String title;

	@Column(nullable = false)
	private int year;

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
