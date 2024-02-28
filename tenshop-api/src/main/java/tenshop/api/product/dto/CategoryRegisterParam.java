package tenshop.api.product.dto;

public record CategoryRegisterParam (
	String name,
	Long parentId
) {
}
