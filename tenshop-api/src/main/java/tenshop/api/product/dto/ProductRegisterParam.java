package tenshop.api.product.dto;

public record ProductRegisterParam(
		String name,
		Integer price,
		Integer stock,
		Long categoryId,
		String content
) {
}
