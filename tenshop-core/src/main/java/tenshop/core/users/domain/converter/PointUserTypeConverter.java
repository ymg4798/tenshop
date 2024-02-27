package tenshop.core.users.domain.converter;

import tenshop.config.converter.AbstractEnumAttributeConverter;
import tenshop.core.users.domain.converter.enums.PointUserType;

public class PointUserTypeConverter extends AbstractEnumAttributeConverter<PointUserType> {
    public static final String enumName = "PointUserType";

    public PointUserTypeConverter() {
        super(PointUserType.class, false, enumName);
    }
}


