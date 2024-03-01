package tenshop.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

public record OrderRegisterParam(
        @NotEmpty String address,
        @PositiveOrZero int usePoint,
        @PositiveOrZero int paymentPrice,
        List<OrderProductsRegisterParam> orderProductsRegisterParams

) {

}
