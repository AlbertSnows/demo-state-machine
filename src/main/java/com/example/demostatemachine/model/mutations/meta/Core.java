package com.example.demostatemachine.model.mutations.meta;

import com.example.demostatemachine.model.data.repositories.redis.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//todo: elaborate
@Service("coreMetaMutations")
public class Core {
	private final Meta metaRepo;

	@Autowired
	public Core(Meta metaRepo) {
		this.metaRepo = metaRepo;
	}

	public void saveEntity(com.example.demostatemachine.model.data.entities.Meta entity) {
		metaRepo.save(entity);
	}

	public Object getEntity(String key) {
		return metaRepo.findById(key).orElse(null);
	}
}
