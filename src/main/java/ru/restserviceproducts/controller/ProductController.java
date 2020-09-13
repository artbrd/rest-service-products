package ru.restserviceproducts.controller;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.restserviceproducts.dto.BaseResponse;
import ru.restserviceproducts.dto.ClientInfoDto;
import ru.restserviceproducts.dto.ProductDto;
import ru.restserviceproducts.dto.RuleDto;
import ru.restserviceproducts.exception.DataNotFoundException;
import ru.restserviceproducts.exception.SaveException;
import ru.restserviceproducts.service.api.ProductService;
import ru.restserviceproducts.service.api.RabbitMqSender;
import ru.restserviceproducts.service.api.RuleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private ResponseEntity responseEntity;

    @Autowired
    private ProductService productService;

    @Autowired
    private RuleService ruleService;

    @Autowired
    RabbitMqSender rabbitMqSender;

    @GetMapping
    public ResponseEntity getAll() {
        List<ProductDto> productDtoList;
        try {
            productDtoList = productService.findAll();
            responseEntity = new ResponseEntity(productDtoList, HttpStatus.OK);
        } catch (DataNotFoundException e) {
            responseEntity = new ResponseEntity(new BaseResponse("Reject", e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping(value = "/{productId}/rules")
    public ResponseEntity getAllRulesProduct(@PathVariable Long productId) {
        List<RuleDto> ruleList;
        try {
            ruleList = ruleService.findAllRulesProduct(productId);
            responseEntity = new ResponseEntity(ruleList, HttpStatus.OK);
        } catch (DataNotFoundException e) {
            responseEntity = new ResponseEntity(new BaseResponse("Reject", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping(value = "/{productId}/rules")
    public ResponseEntity addRule(@PathVariable Long productId,
                                    @Valid @RequestBody RuleDto ruleProduct) {
        ProductDto productDto;
        try {
            productDto = productService.findById(productId);
            ruleService.addRule(productDto, ruleProduct);
            responseEntity = new ResponseEntity(new BaseResponse("Successful"), HttpStatus.OK);
        } catch (DataNotFoundException | SaveException e) {
            responseEntity = new ResponseEntity(new BaseResponse("Reject", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/{productId}/rules/{ruleId}")
    public ResponseEntity deleteRule(@PathVariable Long productId,
                                        @PathVariable Long ruleId) {
        try {
            ruleService.deleteRule(productId, ruleId);
            responseEntity = new ResponseEntity(new BaseResponse("Successful"), HttpStatus.OK);
        } catch (DataNotFoundException | SaveException e) {
            responseEntity = new ResponseEntity(new BaseResponse("Reject", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping(value = "/apply")
    public ResponseEntity getProductForClient (@Valid @RequestBody ClientInfoDto clientInfoDto) {
        List<ProductDto> productDtoList;
        try {
            productDtoList = productService.getProductForClient(clientInfoDto);
            responseEntity = new ResponseEntity(productDtoList, HttpStatus.OK);
        } catch (DataNotFoundException e) {
            responseEntity = new ResponseEntity(new BaseResponse("Reject", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity updateProduct(@PathVariable Long productId,
                                           @Valid @RequestBody ProductDto productDto) {
        try {
            rabbitMqSender.sendMessageUpdate(productId, productDto);
            responseEntity = new ResponseEntity(new BaseResponse("Successful"), HttpStatus.OK);
        } catch (AmqpException e) {
            responseEntity = new ResponseEntity(new BaseResponse("Reject", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity addProduct(@Valid @RequestBody ProductDto productDto) {
        try {
            rabbitMqSender.sendMessageAdd(productDto);
            responseEntity = new ResponseEntity(new BaseResponse("Successful"), HttpStatus.OK);
        }catch (AmqpException e) {
            responseEntity = new ResponseEntity(new BaseResponse("Reject", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity getById(@PathVariable Long productId) {
        ProductDto productDto;
        try {
            productDto = productService.findById(productId);
            responseEntity = new ResponseEntity(productDto, HttpStatus.OK);
        } catch (DataNotFoundException e) {
            responseEntity = new ResponseEntity(new BaseResponse("Reject", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
