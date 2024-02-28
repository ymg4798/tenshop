package tenshop.order.application;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tenshop.core.order.application.OrderService;
import tenshop.core.order.converter.enums.OrderProductsStatus;
import tenshop.core.order.converter.enums.OrderStatus;
import tenshop.core.order.domain.OrderProducts;
import tenshop.order.dto.OrderRegisterParam;

@RequiredArgsConstructor
@Service
public class OrderBroker {

    private final OrderService orderService;

    public String register(OrderRegisterParam param) {
        List<OrderProducts> orderProducts = param.orderProductsRegisterParams().stream()
                .map(v -> OrderProducts.create(v.productId(), OrderProductsStatus.PREPARING, v.quantity()))
                .toList();
        orderService.save(1L, OrderStatus.PREPARING, param.address(), param.usePoint(), param.paymentPrice(), orderProducts);
        return "success";
    }
}


