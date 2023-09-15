package com.example.demostatemachine.model.data.repositories.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@PropertySource("application.properties")
@ConfigurationProperties(prefix = "spring.redis")
@EnableRedisRepositories
public class Redis {
	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;

	@Bean
	public LettuceConnectionFactory redisConnection() {
		var x = new RedisStandaloneConfiguration(host, 6379);
		return new LettuceConnectionFactory(x);
	}
}
