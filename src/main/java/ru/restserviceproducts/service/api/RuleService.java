package ru.restserviceproducts.service.api;

import ru.restserviceproducts.entity.Product;
import ru.restserviceproducts.entity.Rule;

import java.util.List;

public interface RuleService {
    List<Rule> findAllRulesProduct(Long id);
    void addRule(Product product, Rule rule);
    boolean deleteRule(Long productId, Long ruleId);
}
