package tenshop.core.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tenshop.core.users.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
}
