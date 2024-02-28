package tenshop.api.product.dto;

public record ProductRegisterParam(
		String status,
		int price,
		int stock,
		String name,
		String content,
		Long categoryId
) {
}


