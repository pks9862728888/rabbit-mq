package com.example.rabbitmq.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.inbound.AmqpInboundChannelAdapter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class RabbitMQInboundConfig {

    private static final String QUEUE_NAME = "foo-reservation-queue";

    @Bean
    public MessageChannel fooReservationListenerChannel() {
        return new DirectChannel();
    }

//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE_NAME, false);
//    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addQueueNames(QUEUE_NAME);

        return container;
    }

    @Bean
    public AmqpInboundChannelAdapter inbound(SimpleMessageListenerContainer listenerContainer,
                                             @Qualifier("fooReservationListenerChannel") MessageChannel channel) {
        AmqpInboundChannelAdapter adapter = new AmqpInboundChannelAdapter(listenerContainer);
        adapter.setOutputChannel(channel);

        return adapter;
    }
}
