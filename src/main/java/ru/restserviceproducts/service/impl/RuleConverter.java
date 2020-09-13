package ru.restserviceproducts.service.impl;

import org.springframework.stereotype.Component;
import ru.restserviceproducts.dto.RuleDto;
import ru.restserviceproducts.entity.Rule;

@Component
public class RuleConverter {
    public Rule getRule(RuleDto ruleDto) {
        Rule rule = new Rule();
        rule.setId(ruleDto.getId());
        rule.setStartSalary(ruleDto.getStartSalary());
        rule.setEndSalary(ruleDto.getEndSalary());
        rule.setIsDebt(ruleDto.getIsDebt());
        rule.setActive(ruleDto.getActive());
        rule.setDateCreate(ruleDto.getDateCreate());
        rule.setDateUpdate(ruleDto.getDateUpdate());
        rule.setProduct(ruleDto.getProduct());
        return rule;
    }

    public RuleDto getRuleDto(Rule rule) {
        RuleDto ruleDto = new RuleDto();
        ruleDto.setId(rule.getId());
        ruleDto.setStartSalary(rule.getStartSalary());
        ruleDto.setEndSalary(rule.getEndSalary());
        ruleDto.setIsDebt(rule.getIsDebt());
        ruleDto.setActive(rule.getActive());
        ruleDto.setDateCreate(rule.getDateCreate());
        ruleDto.setDateUpdate(rule.getDateUpdate());
        ruleDto.setProduct(rule.getProduct());
        return ruleDto;
    }
}
