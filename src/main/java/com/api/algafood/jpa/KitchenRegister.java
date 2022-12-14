package com.api.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.api.algafood.domain.model.Kitchen;

@Component
public class KitchenRegister {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Kitchen> findAll() {
		TypedQuery<Kitchen> query = manager.createQuery("from Kitchen", Kitchen.class);
		List<Kitchen> kitchens = query.getResultList();
		return kitchens;
	}
	
	public Kitchen findOne(Long id) {
		return manager.find(Kitchen.class, id);
	}
	
	@Transactional
	public Kitchen save(Kitchen kitchen) {
		return manager.merge(kitchen);
	}
	
}
