package tenshop.core.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tenshop.core.product.domain.Category;

public interface CategoryRepositoryCustom {
	Page<Category> findAllBySearchCondition(int depth, Pageable pageable);
}
