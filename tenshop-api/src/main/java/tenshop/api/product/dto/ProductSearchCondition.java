package tenshop.api.product.dto;

public record ProductSearchCondition (
	String name,
	Long categoryId,
	Integer minPrice,
	Integer maxPrice,
	Integer page
) {
}


