package com.example.demostatemachine.model.mutations.meta;

import com.example.demostatemachine.model.data.repositories.redis.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is a KV store of metadata about the application state
 */
@Service("metaReads")
public class Read {
	private final MetaRepository metaRepo;

	@Autowired
	public Read(MetaRepository metaRepo) {
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
