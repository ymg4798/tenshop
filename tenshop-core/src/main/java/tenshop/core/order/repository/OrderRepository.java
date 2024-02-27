package tenshop.core.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tenshop.core.order.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
