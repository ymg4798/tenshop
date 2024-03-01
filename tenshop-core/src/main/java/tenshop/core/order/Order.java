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

    public Order(Long userId, OrderStatus status, String address, int usePoint, int paymentPrice) {
        this.userId = userId;
        this.status = status;
        this.address = address;
        this.usePoint = usePoint;
        this.paymentPrice = paymentPrice;
    }

    public static Order create(Long userId, OrderStatus status, String address, int usePoint, int paymentPrice, List<OrderProducts> orderProducts) {
        Order order = new Order(userId, status, address, usePoint, paymentPrice);
        for (OrderProducts orderProduct : orderProducts) {
            order.addOrderProduct(orderProduct);
        }
        return order;
    }

    public void addOrderProduct(OrderProducts orderProduct){
        this.orderProducts.add(orderProduct);
        orderProduct.setOrder(this);
    }


    public void cancel() {
        if(this.status != OrderStatus.PREPARING) {
            throw new IllegalStateException("주문 취소 가능한 상태가 아닙니다 id : " + id);
        }
        this.status = OrderStatus.REFUND;
        for (OrderProducts orderProduct : orderProducts) {
            orderProduct.cancel();
        }
    }

    public void update(OrderStatus orderStatus) {
        this.status = orderStatus;
    }
}


