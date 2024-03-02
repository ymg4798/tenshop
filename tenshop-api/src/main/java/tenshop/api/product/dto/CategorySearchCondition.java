package tenshop.api.product.dto;

public record CategorySearchCondition(
	int depth,
	Integer page
) {
}
