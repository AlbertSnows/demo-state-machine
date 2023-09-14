package com.example.demostatemachine.model.data.repositories.config;

import com.example.demostatemachine.model.data.entities.Meta;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PreDestroy;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class DataSource {
	private final RedisConnectionFactory redisConnectionFactory;
	@Autowired
	public DataSource(RedisConnectionFactory redisConnectionFactory) {
		this.redisConnectionFactory = redisConnectionFactory;
	}

	@Primary
	@Bean(name = "h2DataSourceProperties")
	@ConfigurationProperties("spring.datasource.h2")
	public DataSourceProperties h2DataSourceProperties() {
		return new DataSourceProperties();
	}
	@Primary
	@Bean(name = "h2DataSource")
	public HikariDataSource h2DataSource(@Qualifier("h2DataSourceProperties") @NotNull DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder()
						.type(HikariDataSource.class)
						.build();
	}
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Meta.class));
		return template;
	}

	private static final Logger logger = LoggerFactory.getLogger(DataSource.class);
	@PreDestroy
	public void onShutdown() {
		logger.info("Flushing redis...");
		redisConnectionFactory.getConnection().serverCommands().flushDb();
	}

}
