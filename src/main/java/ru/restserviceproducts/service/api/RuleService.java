package ru.restserviceproducts.service.api;

import ru.restserviceproducts.entity.Rule;

import java.util.List;

public interface RuleService {
    List<Rule> findAllRulesProduct(Long id);
}
