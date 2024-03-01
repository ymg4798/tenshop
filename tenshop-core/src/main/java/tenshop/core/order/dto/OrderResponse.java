package tenshop.core.order.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import tenshop.core.order.converter.enums.OrderProductsStatus;

@Getter
public class OrderResponse {

    private Long orderProductId;
    private String productName;
    private OrderProductsStatus status;
    private int quantity;
    private int price;

    @QueryProjection
    public OrderResponse(Long orderProductId, String productName, OrderProductsStatus status,
            int quantity, int price) {
        this.orderProductId = orderProductId;
        this.productName = productName;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
    }
}
