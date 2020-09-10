package ru.restserviceproducts.service.api;

import ru.restserviceproducts.entity.Product;

public interface RabbitMqListener {
    void listenAddQueue(Product product);
    void listenUpdateQueue(Product product);
}
