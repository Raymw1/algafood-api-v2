package com.api.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.api.algafood.AlgafoodApiV2Application;
import com.api.algafood.domain.model.Restaurant;
import com.api.algafood.domain.repository.RestaurantRepository;

@SpringBootApplication
public class RestaurantSelectOneMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiV2Application.class)
			.web(WebApplicationType.NONE)
			.run(args);
		RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);
		Restaurant restaurant = restaurantRepository.findOne(1L);
		System.out.printf("Name: %s. Shipping Fee: %f\n", restaurant.getName(), restaurant.getShippingFee());
	}

}
