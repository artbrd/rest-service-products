package ru.restserviceproducts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.restserviceproducts.entity.RuleProduct;

import java.util.List;

@Repository
public interface RuleProductRepository extends JpaRepository<RuleProduct, Long> {
    List<RuleProduct> findByProductsId(Long id);
}
