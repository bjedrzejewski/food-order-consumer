package com.e4developer.foodorderconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@SpringBootApplication
public class FoodOrderConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderConsumerApplication.class, args);
	}

	@StreamListener(target = Sink.INPUT)
	public void processCheapMeals(String meal) throws Exception {
		if(meal.contains("vegetables"))
			throw new Exception("Vegetables! Move to dead letter queue!");
		System.out.println("Meal consumed: "+meal);
	}
}
