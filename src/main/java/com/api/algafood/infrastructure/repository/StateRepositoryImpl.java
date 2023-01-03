package com.api.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.api.algafood.domain.model.State;
import com.api.algafood.domain.repository.StateRepository;

@Component
public class StateRepositoryImpl implements StateRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<State> findAll() {
		List<State> states = manager.createQuery("from State", State.class).getResultList();
		return states;
	}

	@Override
	public State findOne(Long id) {
		State state = manager.find(State.class, id);
		return state;
	}

	@Override
	@Transactional
	public State save(State state) {
		state = manager.merge(state);
		return state;
	}

	@Override
	@Transactional
	public void remove(Long id) {
		State state = this.findOne(id);
		if (state == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(state);
	}

}
