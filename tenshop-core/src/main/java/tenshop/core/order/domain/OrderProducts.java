package tenshop.core.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tenshop.config.auditing.BaseTimeEntity;
import tenshop.core.order.Order;
import tenshop.core.order.converter.enums.OrderProductsStatus;
import tenshop.core.product.converter.ProductStatusConverter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderProducts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private Long productId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Convert(converter = ProductStatusConverter.class)
    @Column(columnDefinition = "varchar(10)")
    private OrderProductsStatus status;

    @Column(columnDefinition = "int(5)")
    private int quantity;
}


