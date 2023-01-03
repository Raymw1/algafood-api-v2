package com.api.algafood.domain.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.algafood.domain.exception.MissingDataException;
import com.api.algafood.domain.exception.UsingEntityException;
import com.api.algafood.domain.model.Kitchen;
import com.api.algafood.domain.model.State;
import com.api.algafood.domain.model.City;
import com.api.algafood.domain.repository.CityRepository;
import com.api.algafood.domain.repository.KitchenRepository;
import com.api.algafood.domain.repository.StateRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	public List<City> findAll() {
		return cityRepository.findAll();
	}
	
	public City findOne(Long id) {
		return cityRepository.findOne(id);
	}
	
	public City save(City city) throws MissingDataException {
		try {
			Long stateId = city.getState().getId();
			State state = stateRepository.findOne(stateId);
			if (state == null) {
				throw new EntityNotFoundException(
					String.format("State with id %d does not exist!", stateId)
				);
			}
			city = cityRepository.save(city);
			return city;
		} catch (InvalidDataAccessApiUsageException e) {
			throw new MissingDataException(
					String.format("State id is missing!")
				);
			}
	}
	
	public City update(City currentCity, City city) throws MissingDataException {
		try {
			Long stateId = city.getState().getId();
			State state = stateRepository.findOne(stateId);
			if (state == null) {
				throw new EntityNotFoundException(
					String.format("State with id %d does not exist!", stateId)
				);
			}
			BeanUtils.copyProperties(city, currentCity, "id", "state");
			currentCity = this.save(currentCity);
			return currentCity;
		} catch (InvalidDataAccessApiUsageException e) {
			throw new MissingDataException(
				String.format("State id is missing!")
			);
		}
	}
	
	public void remove(Long id) {
		try {
			cityRepository.remove(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
				String.format("State with id %d does not exist!", id));
		
		}
	}
	
}
