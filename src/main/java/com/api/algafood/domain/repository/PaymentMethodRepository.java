package com.api.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.api.algafood.domain.model.PaymentMethod;

@Repository
public interface PaymentMethodRepository {
	List<PaymentMethod> findAll();
	PaymentMethod findOne(Long id);
	PaymentMethod save(PaymentMethod paymentMethod);
	void remove(PaymentMethod paymentMethod);
}
