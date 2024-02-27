package tenshop.core.product.converter;

import tenshop.config.converter.AbstractEnumAttributeConverter;
import tenshop.core.product.converter.enums.ProductStatus;

public class ProductStatusConverter extends AbstractEnumAttributeConverter<ProductStatus> {
    public static final String enumName = "ProductStatus";

    public ProductStatusConverter() {
        super(ProductStatus.class, false, enumName);
    }
}


