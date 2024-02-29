package tenshop.api.product.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tenshop.api.product.dto.CategoryRegisterParam;
import tenshop.api.product.dto.CategoryUpdateParam;
import tenshop.core.product.application.CategoryService;
import tenshop.core.product.domain.Category;

@RequiredArgsConstructor
@Service
public class CategoryBroker {

	private final CategoryService categoryService;

	public String save(CategoryRegisterParam param) {
		Category parent = null;
		int depth = 1;

		if (param.parentId() != null) {
			parent = categoryService.findById(param.parentId());
			depth = parent.getDepth() + 1;
		}

		categoryService.save(param.name(), depth, parent);

		return "success";
	}

	public String update(Long id, CategoryUpdateParam param) {
		categoryService.update(id, param.name());
		return "success";
	}
}


