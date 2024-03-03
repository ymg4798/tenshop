package tenshop.core.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tenshop.core.product.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom{
	List<Category> findByParentIsNull();
}


