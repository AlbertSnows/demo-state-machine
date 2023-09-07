package com.example.demostatemachine.model.service.redis.meta;

import com.example.demostatemachine.model.data.entities.Meta;
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

	public Meta put(Meta entity) {
		return metaRepositoryRepo.save(entity);
	}

}
