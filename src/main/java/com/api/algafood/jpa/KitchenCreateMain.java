package com.api.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.api.algafood.AlgafoodApiV2Application;
import com.api.algafood.domain.model.Kitchen;

public class KitchenCreateMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiV2Application.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRegister kitchenRegister = applicationContext.getBean(KitchenRegister.class);
		Kitchen kitchen1 = new Kitchen();
		kitchen1.setName("Italian");
		Kitchen kitchen2 = new Kitchen();
		kitchen2.setName("Japanese");
		kitchen1 = kitchenRegister.create(kitchen1);
		kitchen2 = kitchenRegister.create(kitchen2);
		System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getName());
		System.out.printf("%d - %s\n", kitchen2.getId(), kitchen2.getName());
	}
}
