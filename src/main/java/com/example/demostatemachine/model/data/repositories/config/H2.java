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

}
