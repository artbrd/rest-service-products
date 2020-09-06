package ru.restserviceproducts.service.api;

import ru.restserviceproducts.dto.ClientInfo;
import ru.restserviceproducts.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    List<Product> getProductForClient(ClientInfo clientInfo);
}
