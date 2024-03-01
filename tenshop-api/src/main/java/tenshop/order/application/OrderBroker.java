package tenshop.order.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tenshop.config.converter.EnumConverterUtils;
import tenshop.core.order.application.OrderService;
import tenshop.core.order.converter.enums.OrderProductsStatus;
import tenshop.core.order.converter.enums.OrderStatus;
import tenshop.core.order.domain.OrderProducts;
import tenshop.order.dto.OrderListResponse;
import tenshop.order.dto.OrderRegisterParam;
import tenshop.order.dto.OrderUpdateParam;

@RequiredArgsConstructor
@Service
public class OrderBroker {

    private final OrderService orderService;

    //todo product 있다면 product 재고 감소, 사용자 포인트 있는지 있다면 감소
    public String register(OrderRegisterParam param) {
        List<OrderProducts> orderProducts = param.orderProductsRegisterParams().stream()
                .map(v -> OrderProducts.create(v.productId(), OrderProductsStatus.PREPARING, v.quantity()))
                .toList();
        orderService.save(1L, OrderStatus.PREPARING, param.address(), param.usePoint(), param.paymentPrice(), orderProducts);
        return "success";
    }

    //todo product 있다면 product 재고 증가
    public String cancel(Long orderId) {
        orderService.cancel(orderId);
        return "success";
    }

    public String update(Long orderId, OrderUpdateParam updateParam) {
        OrderStatus orderStatus = EnumConverterUtils.ofName(OrderStatus.class,
                updateParam.orderStatusName());
        orderService.update(orderId, orderStatus);
        return "success";
    }

    public Object findById(Long orderId) {
        return orderService.findById(orderId);
    }


    public OrderListResponse getOrders(int page, int size) {



    }
}



