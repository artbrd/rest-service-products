package ru.restserviceproducts.service.api;

import ru.restserviceproducts.entity.RuleProduct;

import java.util.List;

public interface RuleProductService {
    List<RuleProduct> findAllRulesProduct(Long id);
}
