package com.example.demostatemachine.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("coreQueries")
public class Queries {

	@Qualifier("metaQueries")
	private final com.example.demostatemachine.model.service.redis.meta.Queries metaQueries;

	@Autowired
	public Queries(com.example.demostatemachine.model.service.redis.meta.Queries metaQueries) {
		this.metaQueries = metaQueries;
	}
	public boolean isMetaSeeded() {
		return metaQueries.get("seeded") != null;
	}
}
