package com.api.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.api.algafood.domain.model.PaymentMethod;
import com.api.algafood.domain.repository.PaymentMethodRepository;

@Component
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<PaymentMethod> findAll() {
		List<PaymentMethod> paymentMethods = manager.createQuery("from PaymentMethod", PaymentMethod.class).getResultList();
		return paymentMethods;
	}

	@Override
	public PaymentMethod findOne(Long id) {
		PaymentMethod paymentMethod = manager.find(PaymentMethod.class, id);
		return paymentMethod;
	}

	@Override
	@Transactional
	public PaymentMethod save(PaymentMethod paymentMethod) {
		paymentMethod = manager.merge(paymentMethod);
		return paymentMethod;
	}

	@Override
	@Transactional
	public void remove(PaymentMethod paymentMethod) {
		paymentMethod = this.findOne(paymentMethod.getId());
		manager.remove(paymentMethod);
	}
	
}
