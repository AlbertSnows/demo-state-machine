package com.example.demostatemachine.model.data;

import jakarta.persistence.*;

@Entity
@Table(name = "people")
public class person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	Long get_id() {
		return id;
	}

	String get_name() {
		return name;
	}
}
