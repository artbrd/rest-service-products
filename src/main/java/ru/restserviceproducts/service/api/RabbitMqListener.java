package ru.restserviceproducts.service.api;

import ru.restserviceproducts.dto.ProductDto;

public interface RabbitMqListener {
    void listenAddQueue(ProductDto productDto);
    void listenUpdateQueue(ProductDto productDto);
}
