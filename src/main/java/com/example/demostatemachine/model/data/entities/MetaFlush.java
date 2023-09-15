package com.example.demostatemachine.model.data.entities;

import com.example.demostatemachine.model.data.repositories.config.Redis;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class MetaFlush  implements ApplicationListener<ContextClosedEvent> {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	public MetaFlush(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	private static final Logger logger = LoggerFactory.getLogger(MetaFlush.class);
	@Override
	public void onApplicationEvent(@NotNull ContextClosedEvent event) {
		// Flush the Redis repository when the application is shutting down
		var x = redisTemplate.getConnectionFactory();
 		if(x != null) {
			 logger.info("Flushing db...");
			x.getConnection().serverCommands().flushDb();
		}
	}
}
