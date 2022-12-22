package com.api.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.api.algafood.domain.model.Restaurant;
import com.api.algafood.domain.repository.RestaurantRepository;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurant> findAll() {
		TypedQuery<Restaurant> query = manager.createQuery("from Restaurant", Restaurant.class);
		List<Restaurant> restaurants = query.getResultList();
		return restaurants;
	}

	@Override
	public Restaurant findOne(Long id) {
		Restaurant restaurant = manager.find(Restaurant.class, id);
		return restaurant;
	}

	@Override
	@Transactional
	public Restaurant save(Restaurant restaurant) {
		return manager.merge(restaurant);
	}

	@Override
	@Transactional
	public void remove(Restaurant restaurant) {
		restaurant = this.findOne(restaurant.getId());
		manager.remove(restaurant);
	}
}
