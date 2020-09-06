package ru.restserviceproducts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return ruleRepository.findByProductId(id);
    }
}
