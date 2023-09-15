package com.example.demostatemachine.model.data.repositories.config;

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
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@EnableRedisRepositories
public class H2 {
	private final RedisConnectionFactory redisConnectionFactory;
	@Autowired
	public H2(RedisConnectionFactory redisConnectionFactory) {
		this.redisConnectionFactory = redisConnectionFactory;
	}
	@Bean(name = "h2DataSourceProperties")
	@ConfigurationProperties("spring.datasource.h2")
	public DataSourceProperties h2DataSourceProperties() {
		return new DataSourceProperties();
	}
//	@Primary
	@Bean(name = "h2DataSource")
	public HikariDataSource h2DataSource(@Qualifier("h2DataSourceProperties") @NotNull DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder()
						.type(HikariDataSource.class)
						.build();
	}

//	@Bean
//	@Primary
//	public LettuceConnectionFactory reactiveRedisConnectionFactory(
////					RedisConfiguration defaultRedisConfig
//	) {
//		var x = new RedisStandaloneConfiguration("redis-db-container", 6379);
//		return new LettuceConnectionFactory(x);
////		LettuceClientConfiguration clientConfig =
////						LettuceClientConfiguration.builder()
//////										.useSsl()
////										.build();
////		return new LettuceConnectionFactory(defaultRedisConfig, clientConfig);
//	}
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
	private static final Logger logger = LoggerFactory.getLogger(H2.class);
	@PreDestroy
	public void onShutdown() {
		logger.info("Flushing redis...");
		redisConnectionFactory.getConnection().serverCommands().flushDb();
	}
}
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate() {
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(redisConnectionFactory);
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Meta.class));
//		return template;
//	}