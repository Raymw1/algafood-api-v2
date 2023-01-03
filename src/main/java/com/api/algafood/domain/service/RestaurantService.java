package com.api.algafood.domain.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.algafood.domain.exception.MissingDataException;
import com.api.algafood.domain.model.Kitchen;
import com.api.algafood.domain.model.Restaurant;
import com.api.algafood.domain.repository.KitchenRepository;
import com.api.algafood.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}
	
	public Restaurant findOne(Long id) {
		return restaurantRepository.findOne(id);
	}
	
	public Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenRepository.findOne(kitchenId);
		if (kitchen == null) {
			throw new EntityNotFoundException(
				String.format("Kitchen with id %d does not exist!", kitchenId)
			);
		}
		restaurant.setKitchen(kitchen);
		return restaurantRepository.save(restaurant);
	}

	public Restaurant update(Restaurant currentRestaurant, Restaurant restaurant) throws MissingDataException {
		try {
			Long kitchenId = restaurant.getKitchen().getId();
			Kitchen kitchen = kitchenRepository.findOne(kitchenId);
			if (kitchen == null) {
				throw new EntityNotFoundException(
					String.format("Kitchen with id %d does not exist!", kitchenId)
				);
			}
			
			BeanUtils.copyProperties(restaurant, currentRestaurant, "id");
			currentRestaurant = this.save(currentRestaurant);
			return currentRestaurant;
		} catch (InvalidDataAccessApiUsageException e) {
			throw new MissingDataException(
				String.format("Kitchen id is missing!")
			);
		}
	}

}
