package com.api.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.api.algafood.domain.model.City;

@Repository
public interface CityRepository {
	List<City> findAll();
	City findOne(Long id);
	City save(City city);
	void remove(City city);
}
