package ru.restserviceproducts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.restserviceproducts.dto.ClientInfo;
import ru.restserviceproducts.entity.Product;
import ru.restserviceproducts.exception.rabbit.SaveException;
import ru.restserviceproducts.exception.rabbit.UpdateException;
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
    @Transactional
    public void updateProduct(Product product) throws UpdateException {
        try {
            Product foundProduct = productRepository.findById(product.getId()).orElse(null);

            foundProduct.setName(product.getName());
            foundProduct.setTerm(product.getTerm());
            foundProduct.setEndSumCred(product.getEndSumCred());
            foundProduct.setStartSumCred(product.getStartSumCred());
            foundProduct.setPercent(product.getPercent());
            productRepository.save(foundProduct);
        }
        catch (IllegalArgumentException e1) {
            throw new UpdateException("Product by id " + product.getId() + " is not found");
        }
        catch (Exception e2) {
            throw new UpdateException("Exception update product");
        }
    }

    @Override
    @Transactional
    public void saveProduct(Product product) throws SaveException {
        try {
            productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SaveException("Exception save product");
        }
    }
}
