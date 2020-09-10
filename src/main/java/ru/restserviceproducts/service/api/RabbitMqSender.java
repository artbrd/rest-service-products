package ru.restserviceproducts.service.api;

import ru.restserviceproducts.entity.Product;

public interface RabbitMqSender {
    void sendMessageUpdate(Long productId, Product product);
    void sendMessageAdd(Product product);
}
