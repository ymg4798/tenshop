package tenshop.core.users.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import tenshop.config.auditing.BaseTimeEntity;
import tenshop.core.users.User;
import tenshop.core.users.domain.converter.AddressStatusConverter;
import tenshop.core.users.domain.converter.enums.AddressStatus;

import static tenshop.config.converter.EnumConverterUtils.ofName;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Address extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Convert(converter = AddressStatusConverter.class)
    @Column(columnDefinition = "varchar(10)")
    private AddressStatus status;

    @Column(columnDefinition = "varchar(10)")
    private String city;

    @Column(columnDefinition = "varchar(20)")
    private String street;

    @Column(columnDefinition = "varchar(10)")
    private String zipCode;

    @Builder
    public Address(AddressStatus status, String city, String street, String zipCode) {
        this.status = status;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public void setUser(User user) {
        this.user = user;
        user.getAddresses().add(this);
    }

    public static Address create(String status, String city, String street, String zipCode, User user) {
        Address address = Address.builder()
                .status(ofName(AddressStatus.class, status))
                .city(city)
                .street(street)
                .zipCode(zipCode)
                .build();
        address.setUser(user);
        return address;
    }
}


