package com.example.demostatemachine.model.service.redis.meta;

import com.example.demostatemachine.model.data.repositories.redis.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is a KV store of metadata about the application state
 */
@Service("metaQueries")
public class Queries {
	private final MetaRepository metaRepo;
	@Autowired
	public Queries(MetaRepository metaRepo) {
		this.metaRepo = metaRepo;
	}
	public Object getEntity(String key) {
		return metaRepo.findById(key).orElse(null);
	}
	public Object get(String key) {
		var myDataOptional = metaRepo.findById("seeded");
		var test = metaRepo.findAll();
		return myDataOptional;
	}
}
