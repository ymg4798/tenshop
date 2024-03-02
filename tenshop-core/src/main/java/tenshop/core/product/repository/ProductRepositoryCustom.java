package tenshop.core.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tenshop.core.product.Product;

public interface ProductRepositoryCustom {
	Page<Product> findAllBySearchCondition(String name, Long categoryId, Integer minPrice, Integer maxPrice, Pageable pageable);
}


