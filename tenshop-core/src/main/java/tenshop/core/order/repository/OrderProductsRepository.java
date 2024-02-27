package tenshop.core.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tenshop.core.order.domain.OrderProducts;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {
}
