package ru.restserviceproducts.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restserviceproducts.config.RabbitConfiguration;
import ru.restserviceproducts.dto.ProductDto;
import ru.restserviceproducts.exception.SaveException;
import ru.restserviceproducts.exception.UpdateException;
import ru.restserviceproducts.service.api.ProductService;
import ru.restserviceproducts.service.api.RabbitMqListener;

@Service
public class RabbitMqListenerImpl implements RabbitMqListener {
    @Autowired
    ProductService productService;

    @RabbitListener(queues = RabbitConfiguration.QUEUE_ADD_PRODUCT)
    @Override
    public void listenAddQueue(ProductDto productDto)  throws SaveException {
        try {
            productService.saveProduct(productDto);
        } catch (Exception e) {
            throw new SaveException("Save product faild:\n" + e.getMessage());
        }
    }

    @RabbitListener(queues = RabbitConfiguration.QUEUE_UPDATE_PRODUCT)
    @Override
    public void listenUpdateQueue(ProductDto productDto) throws UpdateException {
        try {
            productService.updateProduct(productDto);
        } catch (Exception e) {
            throw new UpdateException("Update product faild:\n" + e.getMessage());
        }
    }
}
