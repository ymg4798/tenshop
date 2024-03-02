package tenshop.api.product.dto;

import lombok.Builder;
import lombok.Getter;
import tenshop.core.product.domain.Category;

@Getter
public class CategorySearchResponse {
	private Long id;
	private String name;
	private int depth;
	private Long parentId;

	@Builder
	public CategorySearchResponse(Long id, String name, int depth, Long parentId){
		this.id = id;
		this.name = name;
		this.depth = depth;
		this.parentId = parentId;
	}

	public static CategorySearchResponse toResponse(Category category) {
		Long parentId = category.getParent() != null ? category.getParent().getId() : null;
		return CategorySearchResponse.builder()
			.id(category.getId())
			.name(category.getName())
			.depth(category.getDepth())
			.parentId(parentId)
			.build();
	}
}
