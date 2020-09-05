package ru.restserviceproducts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.restserviceproducts.entity.Product;
import ru.restserviceproducts.entity.RuleProduct;
import ru.restserviceproducts.service.api.ProductService;
import ru.restserviceproducts.service.api.RuleProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private RuleProductService ruleProductService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> productList = productService.findAll();
        if (productList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(productList);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Object> getById(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Продукт с идентификатором {" + productId + "} не найден");
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping(value = "/{productId}/rules")
    public ResponseEntity<List<RuleProduct>> getAllRulesProduct(@PathVariable Long productId) {
        List<RuleProduct> ruleProductList = ruleProductService.findAllRulesProduct(productId);
        if (ruleProductList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(ruleProductList);
    }
}
