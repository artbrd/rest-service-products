package ru.restserviceproducts.service.api;

import org.springframework.stereotype.Service;
import ru.restserviceproducts.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
}
