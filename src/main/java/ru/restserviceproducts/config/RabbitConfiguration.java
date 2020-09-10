package ru.restserviceproducts.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    public static final String EXCHANGE_ORDERS = "orders-exchange";
    public static final String QUEUE_ADD_PRODUCT = "queue-add";
    public static final String QUEUE_UPDATE_PRODUCT = "queue-update";
    public static final String ROUTING_KEY_ADD = "ADD";
    public static final String ROUTING_KEY_UPDATE = "UPDATE";

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_ORDERS);
    }

    @Bean
    public Queue appQueueAdd() {
        return new Queue(QUEUE_ADD_PRODUCT);
    }

    @Bean
    public Queue appQueueUpdate() {
        return new Queue(QUEUE_UPDATE_PRODUCT);
    }

    @Bean
    public Binding declareBindingAdd() {
        return BindingBuilder.bind(appQueueAdd()).to(appExchange()).with(ROUTING_KEY_ADD);
    }

    @Bean
    public Binding declareBindingUpdate() {
        return BindingBuilder.bind(appQueueUpdate()).to(appExchange()).with(ROUTING_KEY_UPDATE);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
