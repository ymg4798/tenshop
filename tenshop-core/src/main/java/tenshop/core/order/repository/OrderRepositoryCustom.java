package tenshop.core.order.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tenshop.core.order.Order;
import tenshop.core.order.dto.OrderListResponse;
import tenshop.core.order.dto.OrderResponse;

public interface OrderRepositoryCustom {

    List<OrderResponse> findByOrderId(Long orderId);
    Page<OrderListResponse>findOrders(Long userId,Pageable pageable);

}
