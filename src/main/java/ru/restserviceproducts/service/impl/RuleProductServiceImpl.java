package ru.restserviceproducts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restserviceproducts.entity.RuleProduct;
import ru.restserviceproducts.repository.ProductRepository;
import ru.restserviceproducts.repository.RuleProductRepository;
import ru.restserviceproducts.service.api.ProductService;
import ru.restserviceproducts.service.api.RuleProductService;

import java.util.List;

@Service
public class RuleProductServiceImpl implements RuleProductService {
    @Autowired
    private RuleProductRepository ruleProductRepository;

    @Override
    public List<RuleProduct> findAllRulesProduct(Long id) {
        return ruleProductRepository.findByProductsId(id);
    }
}
