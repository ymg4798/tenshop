package tenshop.api.product.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tenshop.api.product.dto.CategoryRegisterParam;
import tenshop.api.product.dto.CategorySearchCondition;
import tenshop.api.product.dto.CategorySearchResponse;
import tenshop.api.product.dto.CategoryUpdateParam;
import tenshop.config.page.PageModel;
import tenshop.config.page.PageResponse;
import tenshop.core.product.application.CategoryService;
import tenshop.core.product.domain.Category;

@RequiredArgsConstructor
@Service
public class CategoryBroker {

	private final CategoryService categoryService;

	@Transactional
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

	public String delete(Long id) {
		categoryService.delete(id);
		return "success";
	}

	public PageResponse<CategorySearchResponse> findAllBySearchCondition(CategorySearchCondition condition) {
		PageModel<Category> model =
			categoryService.findAllBySearchCondition(condition.depth(), condition.page());

		List<CategorySearchResponse> categorySearchResponses = model.getContents().stream()
			.map(CategorySearchResponse::toResponse)
			.collect(Collectors.toList());

		return new PageResponse<>(model, categorySearchResponses);
	}
}


