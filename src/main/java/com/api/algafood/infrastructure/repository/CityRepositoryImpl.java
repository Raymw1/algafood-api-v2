package com.api.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.api.algafood.domain.model.City;
import com.api.algafood.domain.repository.CityRepository;

@Component
public class CityRepositoryImpl implements CityRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<City> findAll() {
		List<City> cities = manager.createQuery("from City", City.class).getResultList();
		return cities;
	}

	@Override
	public City findOne(Long id) {
		City city = manager.find(City.class, id);
		return city;
	}

	@Override
	@Transactional
	public City save(City city) {
		city = manager.merge(city);
		return city;
	}

	@Override
	@Transactional
	public void remove(City city) {
		city = this.findOne(city.getId());
		manager.remove(city);
	}


}
