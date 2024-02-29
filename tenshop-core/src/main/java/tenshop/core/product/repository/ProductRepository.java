package tenshop.core.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tenshop.core.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
}


