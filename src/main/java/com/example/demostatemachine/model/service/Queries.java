package com.example.demostatemachine.model.service;

import com.example.demostatemachine.model.service.redis.meta.MetaQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("coreQueries")
public class Queries {

	@Qualifier("metaQueries")
	private final MetaQueries metaQueries;

	@Autowired
	public Queries(MetaQueries metaQueries) {
		this.metaQueries = metaQueries;
	}
	public boolean isMetaSeeded() {
		return metaQueries.get("seeded"); // != null;
	}
}
