package com.api.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.algafood.domain.model.Restaurant;
import com.api.algafood.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}
	
	public Restaurant findOne(Long id) {
		return restaurantRepository.findOne(id);
	}

}
