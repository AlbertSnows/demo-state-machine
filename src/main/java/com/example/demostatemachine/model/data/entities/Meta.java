package com.example.demostatemachine.model.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.jetbrains.annotations.Contract;

public class Meta {
}
//
//@SuppressWarnings("unused")
//@Entity
//@Table(name = "movie")
//public class Movie {
//
//	@Id
//	private Long id;
//
//	@Column(nullable = false)
//	private String title;
//
//	@Column()
//	private int releaseYear;
//
//	@Contract(pure = true)
//	public Movie() { }
//
//	public Movie(Long id, String title, int releaseYear) {
//		this.id = id;
//		this.title = title;
//		this.releaseYear = releaseYear;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	String getTitle() {
//		return title;
//	}
//
//	public int getReleaseYear() {
//		return releaseYear;
//	}
//}