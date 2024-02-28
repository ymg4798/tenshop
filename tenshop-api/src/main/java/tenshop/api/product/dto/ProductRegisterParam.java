package tenshop.api.product.dto;

public record ProductRegisterParam(
		String status,
		int stock,
		int price,
		String name,
		String content,
		Long categoryId
) {
}
