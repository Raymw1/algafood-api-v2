package com.api.algafood.api.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.algafood.domain.exception.UsingEntityException;
import com.api.algafood.domain.model.State;
import com.api.algafood.domain.service.StateService;

@RestController
@RequestMapping(value = "/states", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class StateController {
	
	@Autowired
	private StateService stateService;
	
//	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@GetMapping
	public List<State> list() {
		return stateService.findAll();
	}
	
	@GetMapping("/{id}")
	public State findOne(@PathVariable Long id) {
		return stateService.findOne(id);
	}
	
	@PostMapping
	public ResponseEntity<State> save(@RequestBody State state) {
		state = stateService.save(state);
		return ResponseEntity.status(HttpStatus.CREATED).body(state);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<State> update(@PathVariable Long id, @RequestBody State state) {
		State currentState = stateService.findOne(id);
		if (currentState == null) {
			return ResponseEntity.notFound().build();
		}
		currentState = stateService.update(currentState, state);
		return ResponseEntity.ok(currentState);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		try {
			stateService.remove(id);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (UsingEntityException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
