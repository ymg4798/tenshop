package tenshop.core.order.converter.enums;

import lombok.Getter;
import tenshop.config.converter.EnumType;

@Getter
public enum OrderProductsStatus implements EnumType {
    PREPARING("PREPARING", "준비중"),
    READY("READY", "준비완료"),
    REFUND("REFUND", "환불");

    private String code;
    private String name;

    OrderProductsStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }
}


