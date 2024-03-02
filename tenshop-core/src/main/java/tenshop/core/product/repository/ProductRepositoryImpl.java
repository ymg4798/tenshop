package tenshop.core.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;

import tenshop.config.querydsl.QuerydslRepositorySupport;
import tenshop.core.product.Product;
import tenshop.core.product.QProduct;
import tenshop.core.product.domain.QCategory;

public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom{
	public ProductRepositoryImpl() {
		super(Product.class);
	}
	@Override
	public Page<Product> findAllBySearchCondition(String name, String categoryName, Integer minPrice, Integer maxPrice, Pageable pageable) {
		QProduct product = QProduct.product;
		QCategory category = QCategory.category;

		OrderSpecifier<?>[] orderBy = new OrderSpecifier<?>[]{
			product.modifiedDate.coalesce(product.createdDate).desc()
		};

		BooleanExpression categoryCondition = categoryName != null ?
			category.name.eq(categoryName) : null;

		return applyPagination(pageable,
			contentQuery -> contentQuery
				.select(product)
				.from(product)
				.where(
					nameContains(name),
					categoryCondition,
					priceBetween(minPrice, maxPrice)
				)
				.orderBy(orderBy),
			countQuery -> countQuery
				.select(product.count())
				.from(product)
				.where(
					nameContains(name),
					categoryCondition,
					priceBetween(minPrice, maxPrice)
				)
		);
	}

	private BooleanExpression nameContains(String name) {
		return name != null ? QProduct.product.name.containsIgnoreCase(name) : null;
	}

	private BooleanExpression priceBetween(Integer minPrice, Integer maxPrice) {
		if (minPrice != null && maxPrice != null) {
			return QProduct.product.price.between(minPrice, maxPrice);
		} else if (minPrice != null) {
			return QProduct.product.price.goe(minPrice);
		} else if (maxPrice != null) {
			return QProduct.product.price.loe(maxPrice);
		} else {
			return null;
		}
	}
}


