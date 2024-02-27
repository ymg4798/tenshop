package tenshop.core.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tenshop.config.auditing.BaseTimeEntity;
import tenshop.core.order.converter.OrderStatusConverter;
import tenshop.core.order.converter.enums.OrderStatus;
import tenshop.core.order.domain.OrderProducts;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private Long userId;

    @Convert(converter = OrderStatusConverter.class)
    @Column(columnDefinition = "varchar(10)")
    private OrderStatus status;

    @Column(columnDefinition = "varchar(100)")
    private String address;

    @Column(columnDefinition = "int(11)")
    private int usePoint;

    @Column(columnDefinition = "int(11)")
    private int paymentPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<OrderProducts> orderProducts = new ArrayList<>();
}


