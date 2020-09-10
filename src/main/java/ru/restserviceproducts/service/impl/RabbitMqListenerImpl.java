package ru.restserviceproducts.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restserviceproducts.config.RabbitConfiguration;
import ru.restserviceproducts.entity.Product;
import ru.restserviceproducts.service.api.ProductService;
import ru.restserviceproducts.service.api.RabbitMqListener;

@Service
public class RabbitMqListenerImpl implements RabbitMqListener {
    @Autowired
    ProductService productService;

    @RabbitListener(queues = RabbitConfiguration.QUEUE_ADD_PRODUCT)
    @Override
    public void listenAddQueue(Product product) {
        productService.saveProduct(product);
    }

    @RabbitListener(queues = RabbitConfiguration.QUEUE_UPDATE_PRODUCT)
    @Override
    public void listenUpdateQueue(Product product) {
        productService.updateProduct(product);
    }
}
