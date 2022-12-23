package com.api.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.api.algafood.domain.model.City;

@Repository
public interface CityRepository {
	List<CityRepository> findAll();
	CityRepository findOne(Long id);
	CityRepository save(City city);
	void remove(City city);
}
