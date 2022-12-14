package com.api.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.api.algafood.AlgafoodApiV2Application;
import com.api.algafood.domain.model.Kitchen;

public class KitchenSelectMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiV2Application.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRegister kitchenRegister = applicationContext.getBean(KitchenRegister.class);
		List<Kitchen> kitchens = kitchenRegister.list();
		for (Kitchen kitchen : kitchens) {
			System.out.println(kitchen.getName());
		}
	}
}
