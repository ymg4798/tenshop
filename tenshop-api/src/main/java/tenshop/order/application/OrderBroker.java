package tenshop.order.application;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tenshop.core.order.application.OrderService;
import tenshop.core.order.domain.OrderProducts;
import tenshop.order.dto.OrderRegisterParam;

@Service
@RequiredArgsConstructor
public class OrderBroker {

    private final OrderService orderService;
    public String createOrder(Long memberId, OrderRegisterParam registerParam) {

        List<OrderProducts> orderProducts = registerParam.products().stream()
                .map(param -> OrderProducts.create(param.productId(), param.quantity()))
                .toList();
        orderService.save(memberId, registerParam.address(), registerParam.usePoint(), registerParam.paymentPrice(), orderProducts);
        return "success";

    }
}
