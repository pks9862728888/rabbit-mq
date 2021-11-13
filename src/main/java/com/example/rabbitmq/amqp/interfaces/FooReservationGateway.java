package com.example.rabbitmq.amqp.interfaces;

import com.example.rabbitmq.models.FooReservation;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "reservationChannel")
public interface FooReservationGateway {

    void publishReservation(FooReservation fooReservation);

}
