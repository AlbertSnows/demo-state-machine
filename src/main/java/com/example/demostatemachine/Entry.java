package com.example.demostatemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.demostatemachine.model.data.repositories.redis")
public class Entry {

	public static void main(String[] args) {
		SpringApplication.run(Entry.class, args);
	}

}
