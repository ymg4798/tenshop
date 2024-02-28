package tenshop.api.product.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tenshop.api.product.dto.ProductRegisterParam;
import tenshop.api.product.dto.ProductUpdateParam;
import tenshop.core.product.application.CategoryService;
import tenshop.core.product.application.ProductService;
import tenshop.core.product.domain.Category;

@RequiredArgsConstructor
@Service
public class ProductBroker {

	private final ProductService productService;
	private final CategoryService categoryService;

	public String save(ProductRegisterParam param) {
		Category category = categoryService.findById(param.categoryId());
		productService.save(param.status(), param.stock(), param.price(), param.name(), param.content(), category);
		return "success";
	}

	public String update(Long id, ProductUpdateParam param) {
		productService.update(id, param.status());
		return "success";
	}
}
