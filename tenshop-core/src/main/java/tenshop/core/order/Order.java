package tenshop.core.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import tenshop.config.auditing.BaseTimeEntity;
import tenshop.core.order.converter.OrderStatusConverter;
import tenshop.core.order.converter.enums.OrderStatus;
import tenshop.core.order.domain.OrderProducts;

import java.util.ArrayList;
import java.util.List;

@Table(name = "orders")
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

    private int usePoint;

    private int paymentPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<OrderProducts> orderProducts = new ArrayList<>();

    @Builder
    public Order(Long userId, OrderStatus status, String address, int usePoint, int paymentPrice) {
        this.userId = userId;
        this.status = status;
        this.address = address;
        this.usePoint = usePoint;
        this.paymentPrice = paymentPrice;
    }




    public static Order create(Long userId, String address, int usePoint, int paymentPrice, List<OrderProducts> orderProducts) {
        Order order = Order.builder()
                .userId(userId)
                .status(OrderStatus.PREPARING)
                .address(address)
                .usePoint(usePoint)
                .paymentPrice(paymentPrice)
                .build();
        //Order order = new Order(userId, OrderStatus.PREPARING, address, usePoint, paymentPrice);

        for (OrderProducts orderProduct : orderProducts) {
            order.addOrderProduct(orderProduct);
        }

        return order;

    }

    private void addOrderProduct(OrderProducts orderProduct){
        this.orderProducts.add(orderProduct);
        orderProduct.setOrder(this);
    }


}


