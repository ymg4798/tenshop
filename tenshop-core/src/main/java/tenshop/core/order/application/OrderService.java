package tenshop.core.order.application;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tenshop.core.order.Order;
import tenshop.core.order.converter.enums.OrderProductsStatus;
import tenshop.core.order.converter.enums.OrderStatus;
import tenshop.core.order.domain.OrderProducts;
import tenshop.core.order.repository.OrderRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    //private static final OrderStatus DEFAULT_ORDER_STATUS = OrderStatus.PREPARING;
    //private static final OrderProductsStatus DEFAULT_ORDER_PRODUCT_STATUS = OrderProductsStatus.PREPARING;
    public void save(Long userId, String address, int usePoint, int paymentPrice, List<OrderProducts> orderProducts) {
        Order order = Order.create(userId, address, usePoint, paymentPrice, orderProducts);
        orderRepository.save(order);
    }
}
