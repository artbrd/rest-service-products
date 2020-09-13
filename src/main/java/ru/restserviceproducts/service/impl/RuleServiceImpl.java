package ru.restserviceproducts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.restserviceproducts.dto.ProductDto;
import ru.restserviceproducts.dto.RuleDto;
import ru.restserviceproducts.entity.Product;
import ru.restserviceproducts.entity.Rule;
import ru.restserviceproducts.exception.SaveException;
import ru.restserviceproducts.exception.DataNotFoundException;
import ru.restserviceproducts.repository.RuleRepository;
import ru.restserviceproducts.service.api.RuleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private RuleConverter ruleConverter;

    @Autowired
    private ProductConverter productConverter;

    @Override
    @Transactional(readOnly = true)
    public List<RuleDto> findAllRulesProduct(Long id) throws DataNotFoundException {
        List<Rule> ruleList = ruleRepository.findByActiveTrueAndProductId(id);
        if (ruleList.isEmpty()) {
            throw new DataNotFoundException("Can not found rules");
        }
        return ruleList.stream().map(ruleConverter::getRuleDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addRule(ProductDto productDto, RuleDto ruleDto) throws SaveException {
        Product product = productConverter.getProduct(productDto);
        Rule rule = ruleConverter.getRule(ruleDto);
        rule.setProduct(product);
        try {
            ruleRepository.save(rule);
        } catch (Exception e) {
            throw new SaveException("Can not save rule by product id " + product.getId());
        }
    }

    @Override
    @Transactional
    public void deleteRule(Long productId, Long ruleId) throws DataNotFoundException, SaveException {
        Rule rule = ruleRepository.findByProductIdAndId(productId, ruleId).orElse(null);
        if (rule == null) {
            throw new DataNotFoundException("Can not found rule with id: " + ruleId + " for product with id: " + productId);
        }
        rule.setActive(false);
        try {
            ruleRepository.save(rule);
        } catch (Exception e) {
            throw new SaveException(e.getMessage());
        }
    }
}
