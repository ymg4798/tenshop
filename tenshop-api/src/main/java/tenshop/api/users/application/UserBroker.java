package tenshop.api.users.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tenshop.core.users.application.UserService;
import tenshop.core.users.domain.Address;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserBroker {

    private final UserService userService;

    public void save(String email, String password, String name, List<Address> addresses) {
        userService.save(email, password, name, addresses);
    }
}


