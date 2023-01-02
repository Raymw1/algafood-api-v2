package com.api.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.api.algafood.domain.model.Kitchen;
import com.api.algafood.domain.repository.KitchenRepository;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Kitchen> findAll() {
		TypedQuery<Kitchen> query = manager.createQuery("from Kitchen", Kitchen.class);
		List<Kitchen> kitchens = query.getResultList();
		return kitchens;
	}
	
	@Override
	public Kitchen findOne(Long id) {
		return manager.find(Kitchen.class, id);
	}
	
	@Override
	@Transactional
	public Kitchen save(Kitchen kitchen) {
		return manager.merge(kitchen);
	}
	
	@Override
	@Transactional	
	public void remove(Long id) {
		Kitchen kitchen = this.findOne(id);
		if (kitchen == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(kitchen);
	}
}
