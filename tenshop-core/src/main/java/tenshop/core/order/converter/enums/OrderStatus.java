package tenshop.core.order.converter.enums;

import lombok.Getter;
import tenshop.config.converter.EnumType;

@Getter
public enum OrderStatus implements EnumType {
    PREPARING("PREPARING", "준비중"),
    DELIVERY("DELIVERY", "배송중"),
    CONFIRMED("CONFIRMED", "구매확정"),
    REFUND("REFUND", "환불");

    private String code;
    private String name;

    OrderStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }
}


