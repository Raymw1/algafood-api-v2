package com.api.algafood.domain.repository;

import java.util.List;

import com.api.algafood.domain.model.Kitchen;

public interface KitchenRepository {
	List<Kitchen> findAll();
	Kitchen findOne(Long id);
	Kitchen save(Kitchen kitchen);
	void remove(Kitchen kitchen);
}
