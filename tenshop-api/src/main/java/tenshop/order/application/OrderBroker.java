package tenshop.order.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tenshop.config.converter.EnumConverterUtils;
import tenshop.config.page.PageModel;
import tenshop.config.page.PageResponse;
import tenshop.core.order.Order;
import tenshop.core.order.application.OrderService;
import tenshop.core.order.converter.enums.OrderProductsStatus;
import tenshop.core.order.converter.enums.OrderStatus;
import tenshop.core.order.domain.OrderProducts;
import tenshop.core.order.dto.OrderListResponse;
import tenshop.order.dto.OrderRegisterParam;
import tenshop.core.order.dto.OrderResponse;
import tenshop.order.dto.OrderUpdateParam;

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

    public List<OrderResponse> findOrderInformation(Long orderId) {
        return orderService.findOrderInformation(orderId);
    }

    public PageModel<OrderListResponse> findOrders(Long userId, int page, int size) {
        return orderService.findOrders(userId, page, size);
    }
}



