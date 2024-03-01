package tenshop.order.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record OrderProductsRegisterParam(
        @PositiveOrZero Long productId,
        @Positive int quantity
) {

}
