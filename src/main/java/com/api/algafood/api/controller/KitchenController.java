package com.api.algafood.api.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.algafood.api.model.KitchensXmlWrapper;
import com.api.algafood.domain.exception.UsingEntityException;
import com.api.algafood.domain.model.Kitchen;
import com.api.algafood.domain.service.KitchenService;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/kitchens")
public class KitchenController {
	
	@Autowired
	private KitchenService kitchenService;
	
	@GetMapping
	public ResponseEntity<List<Kitchen>> list() {
		List<Kitchen> kitchens = kitchenService.findAll();
		
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
		return new KitchensXmlWrapper(kitchenService.findAll());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public ResponseEntity<Kitchen> findOne(@PathVariable Long id) {
		Kitchen kitchen = kitchenService.findOne(id);
		if (kitchen == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(kitchen);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Kitchen save(@RequestBody Kitchen kitchen) {
		kitchen = kitchenService.save(kitchen);
		return kitchen;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
		Kitchen currentKitchen = kitchenService.findOne(id);
		if (currentKitchen == null) {
			return ResponseEntity.notFound().build();
		}
		currentKitchen = kitchenService.update(currentKitchen, kitchen);
		return ResponseEntity.ok(currentKitchen);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remove(@PathVariable Long id) {
		try {
			kitchenService.remove(id);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (UsingEntityException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
