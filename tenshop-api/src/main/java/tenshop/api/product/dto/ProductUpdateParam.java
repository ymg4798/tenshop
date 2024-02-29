package tenshop.api.product.dto;

public record ProductUpdateParam(
		String status,
		String name,
		String content,
		Integer price,
		Integer stock
) {
}


