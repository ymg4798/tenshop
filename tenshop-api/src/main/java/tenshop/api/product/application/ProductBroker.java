package tenshop.api.product.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tenshop.api.product.dto.ProductRegisterParam;
import tenshop.api.product.dto.ProductSearchCondition;
import tenshop.api.product.dto.ProductSearchResponse;
import tenshop.api.product.dto.ProductUpdateParam;
import tenshop.api.product.dto.ProductUpdateStatusParam;
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
		productService.update(id, param.content(), param.stock());
		return "success";
	}

	public String updateStatus(Long id, ProductUpdateStatusParam param) {
		productService.updateStatus(id, param.status());
		return "success";
	}

	@Transactional
	public PageResponse<ProductSearchResponse> findAllBySearchCondition(ProductSearchCondition condition) {
		PageModel<Product> model =
			productService.findAllBySearchCondition(condition.name(), condition.categoryName(), condition.minPrice(), condition.maxPrice(), condition.page());

		List<ProductSearchResponse> productSearchResponse = model.getContents().stream()
			.map(ProductSearchResponse::toResponse)
			.collect(Collectors.toList());

		return new PageResponse<>(model, productSearchResponse);
	}

	@Transactional
	public void use(Long productId, int quantity) {
		Product product = productService.findById(productId);
		validProductStock(product, quantity);

		int stock = product.getStock() - quantity;
		product.updateStock(stock);
	}

	private void validProductStock(Product product, int quantity) {
		if (product.getStock() < quantity) {
			throw new IllegalArgumentException("재고가 충분하지 않습니다.");
		}
	}

	@Transactional
	public void refund(Long productId, int quantity) {
		Product product = productService.findById(productId);
		validRefundQuantity(quantity);

		int stock = product.getStock() + quantity;
		product.updateStock(stock);
	}

	private void validRefundQuantity(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("환불 수량은 음수일 수 없습니다.");
		}
	}
}


