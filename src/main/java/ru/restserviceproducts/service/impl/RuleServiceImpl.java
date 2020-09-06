package ru.restserviceproducts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.restserviceproducts.entity.Product;
import ru.restserviceproducts.entity.Rule;
import ru.restserviceproducts.repository.RuleRepository;
import ru.restserviceproducts.service.api.RuleService;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    private RuleRepository ruleRepository;

    @Override
    public List<Rule> findAllRulesProduct(Long id) {
        return ruleRepository.findByIsActiveTrueAndProductId(id);
    }

    @Override
    @Transactional
    public void addRule(Product product, Rule rule) {
        rule.setProduct(product);
        ruleRepository.save(rule);
    }

    @Override
    @Transactional
    public boolean deleteRule(Long productId, Long ruleId) {
        Rule rule = ruleRepository.findByProductIdAndId(productId, ruleId).orElse(null);
        if (rule == null) {
            return false;
        }
        rule.setIsActive(false);
        ruleRepository.save(rule);

        return true;
    }
}
