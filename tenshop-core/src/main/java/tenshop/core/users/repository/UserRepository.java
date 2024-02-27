package tenshop.core.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tenshop.core.users.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
