package tenshop.order.dto;

import java.util.List;

public record OrderRegisterParam(
        String address,
        int usePoint,
        int paymentPrice,
        List<OrderProductsRegisterParam> orderProductsRegisterParams

) {

}
