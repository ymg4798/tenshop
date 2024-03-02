package tenshop.api.product.dto;

public record ProductSearchCondition (
	String name,
	String categoryName,
	Integer minPrice,
	Integer maxPrice,
	Integer page
) {
}


