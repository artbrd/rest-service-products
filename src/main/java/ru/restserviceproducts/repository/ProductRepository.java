package ru.restserviceproducts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.restserviceproducts.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByActiveTrueAndId(Long id);
    List<Product> findByActiveTrue();
    @Query(value = "select p.* from product p, rule r where p.id = r.id_product and p.active and r.active and p.start_sum_cred <= :claim and p.end_sum_cred >= :claim and r.start_salary <= :salary and r.end_salary >= :salary and (r.is_debt is null or r.is_debt = :is_debt)", nativeQuery = true)
    List<Product> findProductForClient(@Param("salary") Long salary, @Param("claim") Long claim, @Param("is_debt") Boolean isDebt);
}
