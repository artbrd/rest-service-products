package ru.restserviceproducts.service.api;

import ru.restserviceproducts.dto.ProductDto;

public interface RabbitMqSender {
    void sendMessageUpdate(Long productId, ProductDto productDto);
    void sendMessageAdd(ProductDto productDto);
}
