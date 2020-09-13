package ru.restserviceproducts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.restserviceproducts.dto.ClientInfoDto;
import ru.restserviceproducts.dto.ProductDto;
import ru.restserviceproducts.entity.Product;
import ru.restserviceproducts.exception.SaveException;
import ru.restserviceproducts.exception.UpdateException;
import ru.restserviceproducts.exception.DataNotFoundException;
import ru.restserviceproducts.repository.ProductRepository;
import ru.restserviceproducts.service.api.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findAll() throws DataNotFoundException {
        List<Product> productList = productRepository.findByActiveTrue();
        if (productList.isEmpty()) {
            throw new DataNotFoundException("Can not found products");
        }
        return productList.stream().map(productConverter::getProductDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(Long id) throws DataNotFoundException {
        Product product = productRepository.findByActiveTrueAndId(id).orElse(null);
        if (product == null) {
            throw new DataNotFoundException("Can not found product with id: " + id);
        }
        return productConverter.getProductDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getProductForClient(ClientInfoDto clientInfoDto) throws DataNotFoundException {
        List<Product> productList = productRepository.findProductForClient(clientInfoDto.getSalary(), clientInfoDto.getClaim(), clientInfoDto.getIsDebtor());
        if (productList.isEmpty()) {
            throw new DataNotFoundException("Can not found matching product for client");
        }
        return productList.stream().map(productConverter::getProductDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateProduct(ProductDto productDto) throws UpdateException {
        try {
            Product foundProduct = productRepository.findById(productDto.getId()).orElse(null);
            if (foundProduct == null) {
                throw new UpdateException("Can not found product with id: " + productDto.getId());
            }
            foundProduct.setName(productDto.getName());
            foundProduct.setTerm(productDto.getTerm());
            foundProduct.setEndSumCred(productDto.getEndSumCred());
            foundProduct.setStartSumCred(productDto.getStartSumCred());
            foundProduct.setPercent(productDto.getPercent());
            productRepository.save(foundProduct);
        }
        catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void saveProduct(ProductDto productDto) throws SaveException {
        try {
            productRepository.save(productConverter.getProduct(productDto));
        } catch (Exception e) {
            throw new SaveException(e.getMessage());
        }
    }
}
