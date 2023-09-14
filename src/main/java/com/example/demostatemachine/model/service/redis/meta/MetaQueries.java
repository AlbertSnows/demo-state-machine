package com.example.demostatemachine.model.service.redis.meta;

import com.example.demostatemachine.model.data.entities.Meta;
import com.example.demostatemachine.model.data.repositories.redis.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This is a KV store of metadata about the application state
 */
@Service
public class MetaQueries {
	private final MetaRepository metaRepo;
	@Autowired
	public MetaQueries(MetaRepository metaRepo) {
		this.metaRepo = metaRepo;
	}
	public Object getEntity(String key) {
		return metaRepo.findById(key).orElse(null);
	}
	public boolean get(String key) {
		var test = metaRepo.findAll();
		Optional<Meta> myDataOptional = metaRepo.findById("seeded");
		return myDataOptional.isPresent() && Boolean.TRUE.equals(myDataOptional.get().value);
	}
}
