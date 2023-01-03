package com.api.algafood.api.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.algafood.domain.exception.MissingDataException;
import com.api.algafood.domain.model.Restaurant;
import com.api.algafood.domain.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping
	public List<Restaurant> list() {
		return restaurantService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> findOne(@PathVariable Long id) {
		Restaurant restaurant = restaurantService.findOne(id);
		if (restaurant == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(restaurant);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
		try {
			restaurant = restaurantService.save(restaurant);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
		try {
			Restaurant currentRestaurant = restaurantService.findOne(id);
			if (currentRestaurant == null) {
				return ResponseEntity.notFound().build();
			}
			currentRestaurant = restaurantService.update(currentRestaurant, restaurant);
			return ResponseEntity.ok(currentRestaurant);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (MissingDataException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
