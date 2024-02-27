package tenshop.api.product.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tenshop.api.product.dto.CategoryRegisterParam;
import tenshop.core.product.application.CategoryService;

@RequiredArgsConstructor
@Service
public class CategoryBroker {

	private final CategoryService categoryService;

	public String save(CategoryRegisterParam param) {
		categoryService.save(param.name(), param.parentId());

		return "success";
	}
}
