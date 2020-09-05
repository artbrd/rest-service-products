package ru.restserviceproducts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restserviceproducts.entity.Product;
import ru.restserviceproducts.repository.ProductRepository;
import ru.restserviceproducts.service.api.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Product findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }
}
