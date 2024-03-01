package tenshop.order.dto;

import jakarta.validation.constraints.NotEmpty;

public record OrderUpdateParam(
        @NotEmpty String orderStatusName
) {

}
