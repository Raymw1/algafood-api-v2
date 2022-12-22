package com.api.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.api.algafood.domain.model.Restaurant;

@Repository
public interface RestaurantRepository {
	List<Restaurant> findAll();
	Restaurant findOne(Long id);
	Restaurant save(Restaurant restaurant);
	void remove(Restaurant restaurant);
}
