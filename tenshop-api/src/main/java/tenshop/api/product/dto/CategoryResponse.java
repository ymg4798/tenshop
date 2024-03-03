package tenshop.api.product.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import tenshop.core.product.domain.Category;

@Getter
public class CategoryResponse {
	private Long id;
	private String name;
	private int depth;
	private List<CategoryResponse> children;

	@Builder
	public CategoryResponse(Long id, String name, int depth, List<CategoryResponse> children) {
		this.id = id;
		this.name = name;
		this.depth = depth;
		this.children = children;
	}

	public static CategoryResponse toResponse(Category category, int depth) {
		return CategoryResponse.builder()
			.id(category.getId())
			.name(category.getName())
			.depth(depth)
			.children(category.getChildren().isEmpty() ? new ArrayList<>() :
				category.getChildren().stream()
					.map(child -> toResponse(child, depth + 1))
					.collect(Collectors.toList()))
			.build();
	}
}


