package tenshop.api.product.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import tenshop.core.product.Product;
import tenshop.core.product.domain.Category;

@Getter
public class ProductSearchResponse {
	private Long id;
	private String name;
	private Integer price;
	private Integer stock;
	private String content;
	private String status;
	private String categoryPath;
	private LocalDateTime createdDate;

	@Builder
	public ProductSearchResponse(Long id, String name, Integer price, Integer stock, String content, String status, String categoryPath, LocalDateTime createdDate){
		this.id = id;
		this.name=name;
		this.price = price;
		this.stock = stock;
		this.content = content;
		this.status = status;
		this.categoryPath = categoryPath;
		this.createdDate = createdDate;
	}

	public static ProductSearchResponse toResponse(Product product) {
		StringBuilder categoryPathBuilder = new StringBuilder();
		Category currentCategory = product.getCategory();
		while (currentCategory != null) {
			if (categoryPathBuilder.length() > 0) {
				categoryPathBuilder.insert(0, " > ");
			}
			categoryPathBuilder.insert(0, currentCategory.getName());
			currentCategory = currentCategory.getParent();
		}
		String fullCategoryPath = categoryPathBuilder.toString();

		return ProductSearchResponse.builder()
			.id(product.getId())
			.name(product.getName())
			.price(product.getPrice())
			.stock(product.getStock())
			.content(product.getContent())
			.status(product.getStatus().getName())
			.categoryPath(fullCategoryPath)
			.createdDate(product.getCreatedDate())
			.build();
	}
}


