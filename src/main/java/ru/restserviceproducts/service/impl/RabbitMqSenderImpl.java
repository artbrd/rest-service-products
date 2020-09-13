package ru.restserviceproducts.service.impl;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restserviceproducts.config.RabbitConfiguration;
import ru.restserviceproducts.dto.ProductDto;
import ru.restserviceproducts.service.api.RabbitMqSender;

@Service
public class RabbitMqSenderImpl implements RabbitMqSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessageUpdate(Long productId, ProductDto productDto) throws AmqpException {
        productDto.setId(productId);
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_ORDERS, RabbitConfiguration.ROUTING_KEY_UPDATE, productDto);
    }

    @Override
    public void sendMessageAdd(ProductDto productDto) throws AmqpException {
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_ORDERS, RabbitConfiguration.ROUTING_KEY_ADD, productDto);
    }
}
