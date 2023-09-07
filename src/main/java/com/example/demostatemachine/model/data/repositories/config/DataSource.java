package com.example.demostatemachine.model.data.repositories.config;

import com.zaxxer.hikari.HikariDataSource;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class DataSource {

	@Primary
	@Bean(name = "h2DataSourceProperties")
	@ConfigurationProperties("app.datasource.h2")
	public DataSourceProperties h2DataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "h2DataSource")
	public HikariDataSource h2DataSource(@Qualifier("h2DataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder()
						.type(HikariDataSource.class)
						.build();
	}

	@Bean(name = "redisDataSourceProperties")
	@ConfigurationProperties(prefix = "spring.datasource.redis")
	public DataSourceProperties redisDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "redisConnectionFactory")
	public RedisConnectionFactory redisConnectionFactory(@Qualifier("redisDataSourceProperties") DataSourceProperties redisProperties) {
		var url = redisProperties.getUrl();
//		String redisUrl = new RedisURI("redis://" + url);
//		redisProperties.parse
//		return new LettuceConnectionFactory(redisUrl);
		return new LettuceConnectionFactory();
	}
}
