package com.example.demostatemachine.model.data.entities;

import jakarta.persistence.*;
import org.jetbrains.annotations.Contract;

@SuppressWarnings("unused")
@Entity
@Table(name = "movie")
public class Movie {

	@Id
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column()
	private int releaseYear;

	@Contract(pure = true)
	public Movie() { }

	public Movie(Long id, String title, int releaseYear) {
		this.id = id;
		this.title = title;
		this.releaseYear = releaseYear;
	}

	public Long getId() {
		return id;
	}

	String getTitle() {
		return title;
	}

	public int getReleaseYear() {
		return releaseYear;
	}
}
