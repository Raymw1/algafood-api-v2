package com.api.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.api.algafood.AlgafoodApiV2Application;
import com.api.algafood.domain.model.Kitchen;
import com.api.algafood.domain.repository.KitchenRepository;

public class KitchenCreateMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiV2Application.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);
		Kitchen kitchen1 = new Kitchen();
		kitchen1.setName("Italian");
		Kitchen kitchen2 = new Kitchen();
		kitchen2.setName("Japanese");
		kitchen1 = kitchenRepository.save(kitchen1);
		kitchen2 = kitchenRepository.save(kitchen2);
		System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getName());
		System.out.printf("%d - %s\n", kitchen2.getId(), kitchen2.getName());
	}
}
