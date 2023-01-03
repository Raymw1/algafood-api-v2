package com.api.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.api.algafood.domain.model.State;

@Repository
public interface StateRepository {
	List<State> findAll();
	State findOne(Long id);
	State save(State state);
	void remove(Long id);
}
