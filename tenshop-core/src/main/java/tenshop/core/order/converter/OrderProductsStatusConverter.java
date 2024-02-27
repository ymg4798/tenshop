package tenshop.core.order.converter;

import tenshop.config.converter.AbstractEnumAttributeConverter;
import tenshop.core.order.converter.enums.OrderProductsStatus;

public class OrderProductsStatusConverter  extends AbstractEnumAttributeConverter<OrderProductsStatus> {
    public static final String enumName = "OrderProductsStatus";

    public OrderProductsStatusConverter() {
        super(OrderProductsStatus.class, false, enumName);
    }
}
