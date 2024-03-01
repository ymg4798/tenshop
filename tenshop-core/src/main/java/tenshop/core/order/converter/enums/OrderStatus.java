package tenshop.core.order.converter.enums;

import java.util.List;
import lombok.Getter;
import tenshop.config.converter.EnumType;

@Getter
public enum OrderStatus implements EnumType {
    PREPARING("PREPARING", "준비중", OrderProductsStatus.PREPARING),
    DELIVERY("DELIVERY", "배송중", OrderProductsStatus.PREPARING),
    DELIVERY_COMPLETE("DELIVERY_COMPLETE", "배송완료", OrderProductsStatus.PREPARING),
    CONFIRMED("CONFIRMED", "구매확정", OrderProductsStatus.READY),
    REFUND("REFUND", "환불", OrderProductsStatus.REFUND);

    private String code;
    private String name;
    private OrderProductsStatus orderProductsStatus;
    private List<OrderStatus> orderStatuses;

    OrderStatus(String code, String name, OrderProductsStatus orderProductsStatus) {
        this.code = code;
        this.name = name;
        this.orderProductsStatus = orderProductsStatus;
    }

    static {
        PREPARING.orderStatuses = List.of();
        DELIVERY.orderStatuses = List.of(PREPARING);
        DELIVERY_COMPLETE.orderStatuses = List.of(DELIVERY);
        CONFIRMED.orderStatuses = List.of(DELIVERY_COMPLETE);
        REFUND.orderStatuses = List.of(PREPARING, DELIVERY_COMPLETE);
    }

    public void validateOrderStatusForUpdateBy(OrderStatus status) {
        if (!this.orderStatuses.contains(status)) {
            throw new IllegalStateException("주문 상태 변경이 가능한 상태가 아닙니다");
        }
    }

}


