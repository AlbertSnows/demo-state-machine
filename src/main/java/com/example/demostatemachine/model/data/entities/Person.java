package com.example.demostatemachine.model.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "people")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private final String name;

	public Person(String name) {
		this.name = name;
	}

	public Long get_id() {
		return id;
	}

	public String get_name() {
		return name;
	}
}
