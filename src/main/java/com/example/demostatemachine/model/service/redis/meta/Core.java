package com.example.demostatemachine.model.service.redis.meta;

import com.example.demostatemachine.model.data.repositories.redis.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Core {
	private final MetaRepository metaRepo;

	@Autowired
	public Core(MetaRepository metaRepo) {
		this.metaRepo = metaRepo;
	}
	public Object getEntity(String key) {
		return metaRepo.findById(key).orElse(null);
	}
	public Object get(String key) {
		var myDataOptional = metaRepo.findByKey(key);
		return myDataOptional.orElse(null);
	}
}

