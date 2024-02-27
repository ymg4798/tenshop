package tenshop.core.users.repository.construct;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tenshop.core.users.User;
import tenshop.core.users.domain.Address;
import tenshop.core.users.domain.PointUser;
import tenshop.core.users.domain.converter.enums.AddressStatus;
import tenshop.core.users.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Profile("local")
@RequiredArgsConstructor
@Component
public class UserConstruct {

    private final UserRepository userRepository;

    @PostConstruct
    public void initUser() {
        List<Address> addresses = new ArrayList<>();
        Address address = Address.builder()
                .status(AddressStatus.MAIN)
                .city("서울1")
                .street("서울로1")
                .zipCode("11-222")
                .build();
        addresses.add(address);

        User user = User.create("zzz@test.com", "111", "관리자", addresses);

        user.addPointUser(PointUser.create(user, "적립", "회원가입", 1000, LocalDateTime.now().plusDays(30L)));
        user.addPointUser(PointUser.create(user, "적립", "상품구매", 3000, LocalDateTime.now().plusDays(15L)));
        user.addPointUser(PointUser.create(user, "적립", "상품구매", 1000, LocalDateTime.now().plusDays(10L)));
        user.addPointUser(PointUser.create(user, "만료", "상품구매", 500, LocalDateTime.now().minusDays(10L)));
        user.addPointUser(PointUser.create(user, "사용", "잔여포인트", 500, LocalDateTime.now().minusDays(5L)));

        userRepository.save(user);
    }
}


