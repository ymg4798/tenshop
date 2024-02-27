package tenshop.core.product.converter.enums;

import lombok.Getter;
import tenshop.config.converter.EnumType;

@Getter
public enum ProductStatus implements EnumType {
    PREPARING("PREPARING", "준비중"),
    SALE("SALE", "판매중"),
    SOLD_OUT("SOLD_OUT", "품절"),
    SALE_END("SALE_END", "판매종료");

    private String code;
    private String name;

    ProductStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }
}


