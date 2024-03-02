package tenshop.core.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tenshop.config.querydsl.QuerydslRepositorySupport;
import tenshop.core.product.domain.Category;
import tenshop.core.product.domain.QCategory;

public class CategoryRepositoryImpl extends QuerydslRepositorySupport implements CategoryRepositoryCustom {
	public CategoryRepositoryImpl() {
		super(Category.class);
	}
	@Override
	public Page<Category> findAllBySearchCondition(int depth, Pageable pageable) {
		QCategory category = QCategory.category;

		return applyPagination(pageable,
			query -> query
				.select(category)
				.from(category)
				.where(category.depth.eq(depth)),
			query -> query
				.select(category)
				.from(category)
				.where(category.depth.eq(depth))
		);
	}
}
