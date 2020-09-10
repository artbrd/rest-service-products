package ru.restserviceproducts.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restserviceproducts.config.RabbitConfiguration;
import ru.restserviceproducts.entity.Product;

@Service
public class RabbitMqSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessageUpdate(Long productId, Product product) {
        product.setId(productId);
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_ORDERS, RabbitConfiguration.ROUTING_KEY_UPDATE, product);
    }

    public void sendMessageAdd(Product product) {
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_ORDERS, RabbitConfiguration.ROUTING_KEY_ADD, product);
    }
}
