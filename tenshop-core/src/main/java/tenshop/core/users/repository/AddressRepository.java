package tenshop.core.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tenshop.core.users.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}


