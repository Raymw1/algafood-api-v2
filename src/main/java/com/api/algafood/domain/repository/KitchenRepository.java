package com.api.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.api.algafood.domain.model.Kitchen;

@Repository
public interface KitchenRepository {
	List<Kitchen> findAll();
	Kitchen findOne(Long id);
	Kitchen save(Kitchen kitchen);
	void remove(Kitchen kitchen);
}
