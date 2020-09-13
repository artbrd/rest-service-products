package ru.restserviceproducts.service.api;

import ru.restserviceproducts.dto.ProductDto;
import ru.restserviceproducts.dto.RuleDto;
import ru.restserviceproducts.exception.DataNotFoundException;
import ru.restserviceproducts.exception.SaveException;

import java.util.List;

public interface RuleService {
    List<RuleDto> findAllRulesProduct(Long id) throws DataNotFoundException;
    void addRule(ProductDto productDto, RuleDto ruleDto);
    void deleteRule(Long productId, Long ruleId) throws DataNotFoundException, SaveException;
}
