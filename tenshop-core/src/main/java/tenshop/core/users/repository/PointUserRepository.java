package tenshop.core.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tenshop.core.users.User;
import tenshop.core.users.domain.PointUser;
import tenshop.core.users.domain.converter.enums.PointUserStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface PointUserRepository extends JpaRepository<PointUser, Long> {

    @Query("select COALESCE(sum(p.point), 0) from PointUser p where p.user = :user and p.status = :status and p.expiredDate > :expireDate")
    int getPointByStatusAndExpiredDate(User user, PointUserStatus status, LocalDateTime expireDate);

    List<PointUser> findAllByUserIdOrderByExpiredDate(Long userId);
}


