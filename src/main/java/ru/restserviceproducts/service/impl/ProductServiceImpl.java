package ru.restserviceproducts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restserviceproducts.dto.ClientInfo;
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
        List<Product> productList = productRepository.findByIsActiveTrue();
        return productList;
    }

    @Override
    public Product findById(Long id) {
        Product product = productRepository.findByIsActiveTrueAndId(id).orElse(null);
        return product;
    }

    @Override
    public List<Product> getProductForClient(ClientInfo clientInfo) {
        return productRepository.findProductForClitn(clientInfo.getSalary(), clientInfo.getClaim(), clientInfo.getIsDebtor());
    }

    @Override
    public void updateProduct(Product product) {
        Product foundProduct = productRepository.findById(product.getId()).orElse(null);
        if (foundProduct != null) {
            foundProduct.setName(product.getName());
            foundProduct.setTerm(product.getTerm());
            foundProduct.setEndSumCred(product.getEndSumCred());
            foundProduct.setStartSumCred(product.getStartSumCred());
            foundProduct.setPercent(product.getPercent());
            productRepository.save(foundProduct);
        }
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}
