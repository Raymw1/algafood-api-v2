package com.api.algafood.domain.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.algafood.domain.exception.UsingEntityException;
import com.api.algafood.domain.model.Kitchen;
import com.api.algafood.domain.repository.KitchenRepository;

@Service
public class KitchenService {
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public List<Kitchen> findAll() {
		return kitchenRepository.findAll();
	}
	
	public Kitchen findOne(Long id) {
		return kitchenRepository.findOne(id);
	}
	
	public Kitchen save(Kitchen kitchen) {
		kitchen = kitchenRepository.save(kitchen);
		return kitchen;
	}
	
	public Kitchen update(Kitchen currentKitchen, Kitchen kitchen) {
		BeanUtils.copyProperties(kitchen, currentKitchen, "id");
		currentKitchen = this.save(currentKitchen);
		return currentKitchen;
	}
	
	public void remove(Long id) throws UsingEntityException {
		try {
			kitchenRepository.remove(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
				String.format("Não existe um cadastro de cozinha com código %d", id));
		
		} catch (DataIntegrityViolationException e) {
			throw new UsingEntityException(
				String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
		}
	}
	
}
