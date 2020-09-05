package ru.restserviceproducts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.restserviceproducts.entity.Product;
import ru.restserviceproducts.service.api.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> productList = productService.findAll();
        if (productList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(productList);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Object> getById(@PathVariable long productId) {
        Product product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Продукт с идентификатором {" + productId + "} не найден");
        }
        return ResponseEntity.ok(product);
    }

    /*@GetMapping(value = "/{productId}/rules", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllRulesProduct() {
        Product product = productService.findById(7L);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Продукт с идентификатором {7} не найден");
        }
        return ResponseEntity.ok(product);
    }*/
}
