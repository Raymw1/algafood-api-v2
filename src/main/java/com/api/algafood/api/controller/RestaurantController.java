package com.api.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
