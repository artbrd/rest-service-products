package ru.restserviceproducts.service.impl;

import org.springframework.stereotype.Component;
import ru.restserviceproducts.dto.ProductDto;
import ru.restserviceproducts.entity.Product;

@Component
public class ProductConverter {
    public Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setStartSumCred(productDto.getStartSumCred());
        product.setEndSumCred(productDto.getEndSumCred());
        product.setTerm(productDto.getTerm());
        product.setPercent(productDto.getPercent());
        product.setActive(productDto.getActive());
        product.setDateCreate(productDto.getDateCreate());
        product.setDateUpdate(productDto.getDateUpdate());
        product.setPercent(productDto.getPercent());
        product.setRules(productDto.getRules());
        return product;
    }

    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setStartSumCred(product.getStartSumCred());
        productDto.setEndSumCred(product.getEndSumCred());
        productDto.setTerm(product.getTerm());
        productDto.setPercent(product.getPercent());
        productDto.setActive(product.getActive());
        productDto.setDateCreate(product.getDateCreate());
        productDto.setDateUpdate(product.getDateUpdate());
        productDto.setPercent(product.getPercent());
        productDto.setRules(product.getRules());
        return productDto;
    }
}
