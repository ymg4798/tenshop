package tenshop.api.product.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import tenshop.core.product.Product;

@Getter
public class ProductSearchResponse {
	private Long id;
	private String name;
	private Integer price;
	private Integer stock;
	private String content;
	private String status;
	private Long categoryId;
	private LocalDateTime createdDate;

	@Builder
	public ProductSearchResponse(Long id, String name, Integer price, Integer stock, String content, String status, Long categoryId, LocalDateTime createdDate){
		this.id = id;
		this.name=name;
		this.price = price;
		this.stock = stock;
		this.content = content;
		this.status = status;
		this.categoryId = categoryId;
		this.createdDate = createdDate;
	}

	public static ProductSearchResponse toResponse(Product product) {
		return ProductSearchResponse.builder()
			.id(product.getId())
			.name(product.getName())
			.price(product.getPrice())
			.stock(product.getStock())
			.content(product.getContent())
			.status(product.getStatus().getName())
			.categoryId(product.getCategory().getId())
			.createdDate(product.getCreatedDate())
			.build();
	}
}


