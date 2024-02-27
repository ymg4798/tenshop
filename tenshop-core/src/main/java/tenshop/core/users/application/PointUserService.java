package tenshop.core.users.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tenshop.config.converter.EnumConverterUtils;
import tenshop.core.users.User;
import tenshop.core.users.domain.PointUser;
import tenshop.core.users.domain.converter.enums.PointUserStatus;
import tenshop.core.users.repository.PointUserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static tenshop.config.converter.EnumConverterUtils.ofName;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PointUserService {

    private final PointUserRepository pointUserRepository;

    @Transactional
    public void save(User user, String status, String type, int point, LocalDateTime expiredDate) {
        PointUser pointUser = PointUser.create(user, status, type, point, expiredDate);
        pointUserRepository.save(pointUser);
    }

    public int getPointByStatusAndExpiredDate(User user, String status, LocalDateTime expiredDate) {
        return pointUserRepository.getPointByStatusAndExpiredDate(user, ofName(PointUserStatus.class, status), expiredDate);
    }

    public List<PointUser> findAllByUserIdOrderByExpiredDate(Long userId) {
        return pointUserRepository.findAllByUserIdOrderByExpiredDate(userId);
    }
}


