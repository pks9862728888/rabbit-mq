package com.example.rabbitmq;

import com.example.rabbitmq.models.FooReservation;
import com.example.rabbitmq.services.FooReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RabbitMqApplication implements CommandLineRunner {

	@Autowired
	private FooReservationService service;

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<FooReservation> fooReservations = List.of(
			new FooReservation(1, "One"),
			new FooReservation(2, "Two"),
			new FooReservation(3, "Three"),
			new FooReservation(4, "Four"),
			new FooReservation(5, "Five")
		);
		fooReservations.forEach(r -> {
			service.publishReservation(r);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
}
