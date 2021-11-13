package com.example.rabbitmq.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.outbound.AmqpOutboundEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.messaging.MessageChannel;

@Configuration
public class RabbitMQOutboundConfig {

    private static final String QUEUE_NAME = "foo-reservation-queue";

    @Bean
    public MessageChannel amqpOutboundChannel() {
        return new DirectChannel();
    }

    @Bean()
    public Queue queue() { // Creates queue automatically if not created
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    @ServiceActivator(inputChannel = "amqpOutboundChannel")
    public AmqpOutboundEndpoint amqpOutboundEndpoint(AmqpTemplate amqpTemplate) {
        AmqpOutboundEndpoint amqpOutboundEndpoint = new AmqpOutboundEndpoint(amqpTemplate);
        amqpOutboundEndpoint.setRoutingKey(QUEUE_NAME);

        return amqpOutboundEndpoint;
    }

    @Bean
    public MessageChannel reservationChannel() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "reservationChannel", outputChannel = "amqpOutboundChannel")
    public ObjectToJsonTransformer objectToJsonTransformer() {
        return new ObjectToJsonTransformer();
    }

}
