package com.example.demostatemachine.model.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "people")
public class person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long id;

	@Column(nullable = false, unique = true)
	private final String name;

	public person(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	Long get_id() {
		return id;
	}

	String get_name() {
		return name;
	}
}
