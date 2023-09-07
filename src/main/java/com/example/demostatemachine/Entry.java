package com.example.demostatemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = "com.example.demostatemachine")
//@ComponentScan("com.example.demostatemachine.model.data.repositories.redis")
@EnableJpaRepositories(basePackages = "com.example.demostatemachine.model.data.repositories.H2")
@EnableRedisRepositories(basePackages = "com.example.demostatemachine.model.data.repositories.redis")
public class Entry {

	public static void main(String[] args) {
		SpringApplication.run(Entry.class, args);
	}

}
