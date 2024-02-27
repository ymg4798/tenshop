package tenshop.core.users.domain.converter.enums;

import lombok.Getter;
import tenshop.config.converter.EnumType;

@Getter
public enum PointUserStatus implements EnumType {
    ACCUMULATE("ACCUMULATE", "적립"),
    USE("USE", "사용"),
    EXPIRED("EXPIRED", "만료");

    private String code;
    private String name;

    PointUserStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }
}


