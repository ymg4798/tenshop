package tenshop.core.users;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tenshop.core.users.domain.Address;
import tenshop.core.users.domain.Point;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(columnDefinition = "varchar(40)")
    private String email;

    @Column(columnDefinition = "varchar(20)")
    private String password;

    @Column(columnDefinition = "varchar(5)")
    private String name;

    private LocalDateTime createdDate = LocalDateTime.now();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Point> points = new ArrayList<>();
}


