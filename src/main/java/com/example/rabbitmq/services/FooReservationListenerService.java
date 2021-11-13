package com.example.rabbitmq.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class FooReservationListenerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ServiceActivator(inputChannel = "fooReservationListenerChannel")
    public void handleMessage(Message message) {
        logger.info("Received message: {}", message.getPayload());
    }
}
