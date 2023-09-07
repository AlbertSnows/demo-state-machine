package com.example.demostatemachine.model.service.redis.meta;

import com.example.demostatemachine.model.data.repositories.redis.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("metaWrites")
public class Mutations {
	private final MetaRepository metaRepositoryRepo;

	@Autowired
	public Mutations(MetaRepository metaRepositoryRepo) {
		this.metaRepositoryRepo = metaRepositoryRepo;
	}

	public void saveEntity(com.example.demostatemachine.model.data.entities.Meta entity) {
		metaRepositoryRepo.save(entity);
	}

}
