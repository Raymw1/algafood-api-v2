package com.api.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.algafood.domain.model.State;
import com.api.algafood.domain.repository.StateRepository;

@RestController
@RequestMapping(value = "/states", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class StateController {

	@Autowired
	private StateRepository stateRepository;
	
//	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@GetMapping
	public List<State> list() {
		return stateRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public State findOne(@PathVariable Long id) {
		return stateRepository.findOne(id);
	}
	
}
