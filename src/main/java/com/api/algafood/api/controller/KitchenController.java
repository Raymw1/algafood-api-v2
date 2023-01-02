package com.api.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.algafood.api.model.KitchensXmlWrapper;
import com.api.algafood.domain.model.Kitchen;
import com.api.algafood.domain.repository.KitchenRepository;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/kitchens")
public class KitchenController {
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	@GetMapping
	public ResponseEntity<List<Kitchen>> list() {
		List<Kitchen> kitchens = kitchenRepository.findAll();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.LOCATION, "http://localhost:3333/kitchens");
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.headers(headers)
//				.build()
				.body(kitchens);
//		return ResponseEntity.ok(kitchens);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public KitchensXmlWrapper listXml() {
		return new KitchensXmlWrapper(kitchenRepository.findAll());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public ResponseEntity<Kitchen> findOne(@PathVariable Long id) {
		Kitchen kitchen = kitchenRepository.findOne(id);
		if (kitchen == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(kitchen);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Kitchen save(@RequestBody Kitchen kitchen) {
		kitchen = kitchenRepository.save(kitchen);
		return kitchen;
	}
}
