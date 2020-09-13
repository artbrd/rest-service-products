package ru.restserviceproducts.service.api;

import ru.restserviceproducts.dto.ClientInfoDto;
import ru.restserviceproducts.dto.ProductDto;
import ru.restserviceproducts.exception.DataNotFoundException;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll() throws DataNotFoundException;
    ProductDto findById(Long id) throws DataNotFoundException;
    List<ProductDto> getProductForClient(ClientInfoDto clientInfoDto) throws DataNotFoundException;
    void updateProduct(ProductDto productDto);
    void saveProduct(ProductDto productDto) throws Exception;
}
