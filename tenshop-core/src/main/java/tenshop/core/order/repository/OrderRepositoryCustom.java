package tenshop.core.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tenshop.core.order.Order;

public interface OrderRepositoryCustom {

    Page<Order> findByUserId(Long userId, Pageable pageable);

}
