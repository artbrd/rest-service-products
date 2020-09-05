package ru.restserviceproducts.service.api;

import org.springframework.stereotype.Service;
import ru.restserviceproducts.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public Product findById(Long id);
}
