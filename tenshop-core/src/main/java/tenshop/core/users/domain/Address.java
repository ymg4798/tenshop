package tenshop.core.users.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tenshop.config.auditing.BaseTimeEntity;
import tenshop.core.users.Users;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Address extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @Column(columnDefinition = "varchar(10)")
    private String city;

    @Column(columnDefinition = "varchar(20)")
    private String street;

    @Column(columnDefinition = "varchar(10)")
    private String zip_code;
}


