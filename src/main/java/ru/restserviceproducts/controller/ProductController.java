package ru.restserviceproducts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.restserviceproducts.dto.ClientInfo;
import ru.restserviceproducts.entity.Product;
import ru.restserviceproducts.entity.Rule;
import ru.restserviceproducts.service.api.ProductService;
import ru.restserviceproducts.service.api.RuleService;
import ru.restserviceproducts.service.impl.RabbitMqSender;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private RuleService ruleService;

    @Autowired
    RabbitMqSender rabbitMqSender;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> productList = productService.findAll();
        if (productList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(productList);
    }

    @GetMapping(value = "/{productId}/rules")
    public ResponseEntity<List<Rule>> getAllRulesProduct(@PathVariable Long productId) {
        List<Rule> ruleList = ruleService.findAllRulesProduct(productId);
        if (ruleList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(ruleList);
    }

    @PostMapping(value = "/{productId}/rules")
    public ResponseEntity<?> addRule(@PathVariable Long productId,
                        @Valid @RequestBody Rule ruleProduct) {
        Product product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ruleService.addRule(product, ruleProduct);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping(value = "/{productId}/rules/{ruleId}")
    public ResponseEntity<?> deleteRule(@PathVariable Long productId,
                                        @PathVariable Long ruleId) {
        if (!ruleService.deleteRule(productId, ruleId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/apply")
    public ResponseEntity<List<Product>> getProductForClient (@Valid @RequestBody ClientInfo clientInfo) {
        List<Product> productList = productService.getProductForClient(clientInfo);
        if (productList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(productList);
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId,
                                           @Valid @RequestBody Product product) {
        rabbitMqSender.sendMessageUpdate(productId, product);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
        rabbitMqSender.sendMessageAdd(product);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Object> getById(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Продукт с идентификатором '" + productId + "' не найден");
        }
        return ResponseEntity.ok(product);
    }
}
