package ru.restserviceproducts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.restserviceproducts.entity.Rule;

import java.util.List;
import java.util.Optional;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
    List<Rule> findByActiveTrueAndProductId(Long id);
    Optional<Rule> findByProductIdAndId(Long productId, Long ruleId);
}
