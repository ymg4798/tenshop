package tenshop.api.product.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tenshop.api.product.dto.ProductRegisterParam;
import tenshop.core.product.application.ProductService;

@RequiredArgsConstructor
@Service
public class ProductBroker {

	private final ProductService productService;

	public String save(ProductRegisterParam param) {
		productService.save(param.name(), param.price(),  param.stock(), param.categoryId());
		return "success";
	}
}
