package com.example.demostatemachine.model.data.repositories.config;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import java.util.List;

@Configuration
@PropertySource("application.properties")
@ConfigurationProperties(prefix = "spring.redis")
@EnableRedisRepositories
public class Redis {
	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
//	private final RedisConnectionFactory redisConnectionFactory;
	private static final Logger logger = LoggerFactory.getLogger(Redis.class);

	@Bean
	public void printRedisConfig() {
		logger.info("Redis Host: {}", host);
		logger.info("Redis Port: {}",  port);
	}

//	@Autowired
//	public Redis(RedisConnectionFactory redisConnectionFactory) {
////		this.redisConnectionFactory = redisConnectionFactory;
//	}
//	@Bean
//	public LettuceConnectionFactory redisConnectionFactory() {
//		logger.info("Connection data: {}", List.of(this.host, this.port));
//		var RSC = new RedisStandaloneConfiguration(this.host, this.port);
//		return new LettuceConnectionFactory(RSC);
//	}
//	@Bean
//	public RedisProperties redisProperties() {
//		return new RedisProperties();
//	}
	@Bean
	@Primary
	public LettuceConnectionFactory reactiveRedisConnectionFactory(
//					RedisConfiguration defaultRedisConfig
	) {
		var x = new RedisStandaloneConfiguration("redis-db-container", 6379);
		return new LettuceConnectionFactory(x);
//		LettuceClientConfiguration clientConfig =
//						LettuceClientConfiguration.builder()
////										.useSsl()
//										.build();
//		return new LettuceConnectionFactory(defaultRedisConfig, clientConfig);
	}
//	@Bean
//	public RedisConfiguration defaultRedisConfig() {
////		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
////		config.setHostName("demostatemachine-redis-db-1");
//////		config.set
////		config.setPort(6379);
//////		config.set
//////		config.setPassword(RedisPassword.of("poop"));
////		return config;
//////		return new RedisStandaloneConfiguration();
//	}
	@PreDestroy
	public void onShutdown() {
		logger.info("Flushing redis...");
//		redisConnectionFactory.getConnection().serverCommands().flushDb();
	}
}
