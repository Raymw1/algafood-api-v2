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
import com.api.algafood.domain.model.State;
import com.api.algafood.domain.repository.KitchenRepository;
import com.api.algafood.domain.repository.StateRepository;

@Service
public class StateService {
	
	@Autowired
	private StateRepository stateRepository;
	
	public List<State> findAll() {
		return stateRepository.findAll();
	}
	
	public State findOne(Long id) {
		return stateRepository.findOne(id);
	}
	
	public State save(State state) {
		state = stateRepository.save(state);
		return state;
	}
	
	public State update(State currentState, State state) {
		BeanUtils.copyProperties(state, currentState, "id");
		currentState = this.save(currentState);
		return currentState;
	}
	
	public void remove(Long id) throws UsingEntityException {
		try {
			stateRepository.remove(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
				String.format("State with id %d does not exist!", id));
		
		} catch (DataIntegrityViolationException e) {
			throw new UsingEntityException(
				String.format("State with id %d can't be removed, because it's in use!", id));
		}
	}
	
}
