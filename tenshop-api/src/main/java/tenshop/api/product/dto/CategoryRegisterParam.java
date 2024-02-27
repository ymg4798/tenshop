package tenshop.api.product.dto;

public record CategoryRegisterParam (
	String name,
	Integer parentId
) {
}
