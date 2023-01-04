package com.api.algafood.api.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.algafood.domain.exception.MissingDataException;
import com.api.algafood.domain.exception.UsingEntityException;
import com.api.algafood.domain.model.City;
import com.api.algafood.domain.service.CityService;
import com.api.algafood.domain.service.StateService;

@RestController
@RequestMapping(value = "/cities")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
//	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@GetMapping
	public List<City> list() {
		return cityService.findAll();
	}
	
	@GetMapping("/{id}")
	public City findOne(@PathVariable Long id) {
		return cityService.findOne(id);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody City city) {
		try {
			city = cityService.save(city);
			return ResponseEntity.status(HttpStatus.CREATED).body(city);
		} catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (MissingDataException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody City city) {
		try {
			City currentCity = cityService.findOne(id);
			if (currentCity == null) {
				return ResponseEntity.notFound().build();
			}
			currentCity = cityService.update(currentCity, city);
			return ResponseEntity.ok(currentCity);
		} catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (MissingDataException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		try {
			cityService.remove(id);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
