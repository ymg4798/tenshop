package tenshop.core.users;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tenshop.core.users.domain.Address;
import tenshop.core.users.domain.PointUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PointUser> pointUsers = new ArrayList<>();

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
        address.setUser(this);
    }

    public void addPointUser(PointUser pointUser) {
        this.pointUsers.add(pointUser);
        pointUser.setUser(this);
    }

    public static User create(String email, String password, String name, List<Address> addresses) {
        User user = new User(email, password, name);
        for (Address address : addresses) {
            user.addAddress(address);
        }
        return user;
    }
}


