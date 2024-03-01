package tenshop.core.order.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Getter;
import tenshop.core.order.converter.enums.OrderStatus;

@Getter
public class OrderListResponse {

    private Long orderId;
    //private String name;
    private String address;
    private int paymentPrice;
    private int usePoint;
    private OrderStatus status;
    private LocalDateTime createdDate;


    @QueryProjection
    public OrderListResponse(Long orderId, String address, int paymentPrice, int usePoint,
            OrderStatus status, LocalDateTime createdDate) {
        this.orderId = orderId;
        this.address = address;
        this.paymentPrice = paymentPrice;
        this.usePoint = usePoint;
        this.status = status;
        this.createdDate = createdDate;
    }
}
