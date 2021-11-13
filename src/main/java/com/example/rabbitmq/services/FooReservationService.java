package com.example.rabbitmq.services;

import com.example.rabbitmq.amqp.interfaces.FooReservationGateway;
import com.example.rabbitmq.models.FooReservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooReservationService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private FooReservationGateway fooReservationGateway;

    public void publishReservation(FooReservation reservation) {
        logger.info("Publishing reservation: {}", reservation);
        fooReservationGateway.publishReservation(reservation);
    }
}
