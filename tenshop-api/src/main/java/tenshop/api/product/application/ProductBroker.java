package tenshop.api.product.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tenshop.api.product.dto.ProductBuyParam;
import tenshop.api.product.dto.ProductRegisterParam;
import tenshop.api.product.dto.ProductSearchCondition;
import tenshop.api.product.dto.ProductSearchResponse;
import tenshop.api.product.dto.ProductUpdateParam;
import tenshop.config.page.PageModel;
import tenshop.config.page.PageResponse;
import tenshop.core.product.Product;
import tenshop.core.product.application.CategoryService;
import tenshop.core.product.application.ProductService;
import tenshop.core.product.domain.Category;

@RequiredArgsConstructor
@Service
public class ProductBroker {

	private final ProductService productService;
	private final CategoryService categoryService;

	@Transactional
	public String save(ProductRegisterParam param) {
		Category category = categoryService.findById(param.categoryId());
		productService.save(param.status(), param.stock(), param.price(), param.name(), param.content(), category);
		return "success";
	}

	public String update(Long id, ProductUpdateParam param) {
		productService.update(id, param.status(), param.name(), param.content(), param.price(), param.stock());
		return "success";
	}

	public PageResponse<ProductSearchResponse> findProductsBySearchCondition(ProductSearchCondition condition) {
		PageModel<Product> model =
			productService.findProductsBySearchCondition(condition.name(), condition.categoryId(), condition.minPrice(), condition.maxPrice(), condition.page());

		List<ProductSearchResponse> productSearchResponse = model.getContents().stream()
			.map(ProductSearchResponse::toResponse)
			.collect(Collectors.toList());

		return new PageResponse<>(model, productSearchResponse);
	}

	public String delete(Long id) {
		productService.delete(id);
		return "success";
	}

	@Transactional
	public String buy(ProductBuyParam param, Long userId) {
		param.buys().forEach(buy -> {
			productService.buy(buy.productId(), buy.quantity(), userId);
		});
		return "success";
	}
}


