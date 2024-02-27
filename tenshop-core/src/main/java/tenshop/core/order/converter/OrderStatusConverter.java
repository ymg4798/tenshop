package tenshop.core.order.converter;

import tenshop.config.converter.AbstractEnumAttributeConverter;
import tenshop.core.order.converter.enums.OrderStatus;
import tenshop.core.product.converter.enums.ProductStatus;

public class OrderStatusConverter extends AbstractEnumAttributeConverter<OrderStatus> {
    public static final String enumName = "OrderStatus";

    public OrderStatusConverter() {
        super(OrderStatus.class, false, enumName);
    }
}
