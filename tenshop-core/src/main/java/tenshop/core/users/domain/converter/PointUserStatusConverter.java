package tenshop.core.users.domain.converter;

import tenshop.config.converter.AbstractEnumAttributeConverter;
import tenshop.core.users.domain.converter.enums.PointUserStatus;

public class PointUserStatusConverter extends AbstractEnumAttributeConverter<PointUserStatus> {
    public static final String enumName = "PointUserStatus";

    public PointUserStatusConverter() {
        super(PointUserStatus.class, false, enumName);
    }
}


