package com.api.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.api.algafood.AlgafoodApiV2Application;
import com.api.algafood.domain.model.Restaurant;
import com.api.algafood.domain.repository.RestaurantRepository;
import com.fasterxml.jackson.core.io.BigDecimalParser;

@SpringBootApplication
public class RestaurantUpdateMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiV2Application.class)
			.web(WebApplicationType.NONE)
			.run(args);
		RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);
		Restaurant restaurant = new Restaurant();
		restaurant.setId(1L);
		restaurant.setName("Rei do Sushi");
		restaurant.setShippingFee(BigDecimalParser.parse("10"));
		restaurant = restaurantRepository.save(restaurant);
		System.out.printf("Id: %d.Name: %s. Shipping Fee: %f\n", restaurant.getId(), restaurant.getName(), restaurant.getShippingFee());
	}

}
