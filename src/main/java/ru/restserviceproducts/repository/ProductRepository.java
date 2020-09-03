package ru.restserviceproducts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.restserviceproducts.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
