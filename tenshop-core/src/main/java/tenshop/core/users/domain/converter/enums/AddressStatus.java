package tenshop.core.users.domain.converter.enums;

import lombok.Getter;
import tenshop.config.converter.EnumType;

@Getter
public enum AddressStatus implements EnumType {
    MAIN("MAIN", "대표주소"),
    SUB("SUB", "서브주소");

    private String code;
    private String name;

    AddressStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }
}


