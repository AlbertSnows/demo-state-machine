package com.example.demostatemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(basePackages = "com.example.demostatemachine.model.data.repositories.H2")
@EnableRedisRepositories(basePackages = "com.example.demostatemachine.model.data.repositories.redis")
public class Entry {

	public static void main(String[] args) {
		SpringApplication.run(Entry.class, args);
	}

}
