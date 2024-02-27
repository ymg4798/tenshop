package tenshop.core.users.domain.converter.enums;

import lombok.Getter;
import tenshop.config.converter.EnumType;

@Getter
public enum PointUserType implements EnumType {
    REGISTER("REGISTER", "회원가입"),
    PRODUCT_BUY("PRODUCT_BUY", "상품구매"),
    REMAIN_POINT("REMAIN_POINT", "잔여포인트");

    private String code;
    private String name;

    PointUserType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}


