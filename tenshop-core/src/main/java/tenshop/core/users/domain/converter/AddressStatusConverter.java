package tenshop.core.users.domain.converter;

import tenshop.config.converter.AbstractEnumAttributeConverter;
import tenshop.core.users.domain.converter.enums.AddressStatus;

public class AddressStatusConverter extends AbstractEnumAttributeConverter<AddressStatus> {
    public static final String enumName = "AddressStatus";

    public AddressStatusConverter() {
        super(AddressStatus.class, false, enumName);
    }
}


