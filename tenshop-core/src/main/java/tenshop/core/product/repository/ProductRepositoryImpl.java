package tenshop.core.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.dsl.BooleanExpression;
import tenshop.config.querydsl.QuerydslRepositorySupport;
import tenshop.core.product.Product;
import tenshop.core.product.QProduct;

public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom{
	public ProductRepositoryImpl() {
		super(Product.class);
	}
	@Override
	public Page<Product> findProductsBySearchCondition(String name, Long categoryId, Integer minPrice, Integer maxPrice, Pageable pageable) {
		QProduct product = QProduct.product;

		return applyPagination(pageable,
			contentQuery -> contentQuery
				.select(product)
				.from(product)
				.where(
					nameContains(name),
					categoryIdEq(categoryId),
					priceBetween(minPrice, maxPrice)
				),
			countQuery -> countQuery
				.select(product.count())
				.from(product)
				.where(
					nameContains(name),
					categoryIdEq(categoryId),
					priceBetween(minPrice, maxPrice)
				));
	}

	private BooleanExpression nameContains(String name) {
		return name != null ? QProduct.product.name.containsIgnoreCase(name) : null;
	}

	private BooleanExpression categoryIdEq(Long categoryId) {
		return categoryId != null ? QProduct.product.category.id.eq(categoryId) : null;
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


