package tenshop.core.order.application;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tenshop.config.page.PageModel;
import tenshop.config.page.PageRequest;
import tenshop.core.order.Order;
import tenshop.core.order.converter.enums.OrderStatus;
import tenshop.core.order.domain.OrderProducts;
import tenshop.core.order.dto.OrderListResponse;
import tenshop.core.order.dto.OrderResponse;
import tenshop.core.order.repository.OrderRepository;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void save(Long userId, OrderStatus status, String address, int usePoint,
            int paymentPrice, List<OrderProducts> orderProducts) {
        Order order = Order.create(userId, status, address, usePoint, paymentPrice, orderProducts);
        orderRepository.save(order);
    }

    @Transactional
    public void cancel(Long orderId) {
        Order order = findById(orderId);
        order.cancel();
        orderRepository.save(order);
    }

    @Transactional
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 주문이 존재하지 않습니다. id : " + orderId);
        });
    }

    @Transactional
    public List<OrderResponse> findOrderInformation(Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }


    @Transactional
    public void update(Long orderId, OrderStatus orderStatus) {
        Order order = findById(orderId);
        order.update(orderStatus);
        orderRepository.save(order);
    }

    public PageModel<OrderListResponse> findOrders(Long userId, int page, int size) {
        return new PageModel<>(orderRepository.findOrders(userId, new PageRequest(page,size).of()));
    }
}


