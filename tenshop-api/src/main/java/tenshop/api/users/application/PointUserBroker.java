package tenshop.api.users.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tenshop.core.users.User;
import tenshop.core.users.application.PointUserService;
import tenshop.core.users.application.UserService;
import tenshop.core.users.domain.PointUser;
import tenshop.core.users.domain.converter.enums.PointUserStatus;
import tenshop.core.users.domain.converter.enums.PointUserType;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PointUserBroker {

    private final PointUserService pointUserService;
    private final UserService userService;

    @Transactional
    public void use(Long userId, int usePoint) {
        User user = userService.findById(userId);
        validUsePoint(user, usePoint);

        List<PointUser> pointUsers = pointUserService.findAllByUserIdOrderByExpiredDate(user.getId());
        calculateUsePoint(usePoint, user, pointUsers);
    }

    private void validUsePoint(User user, int usePoint) {
        String status = PointUserStatus.ACCUMULATE.getName();
        int currentPoint = pointUserService.getPointByStatusAndExpiredDate(user, status, LocalDateTime.now());

        if (usePoint > currentPoint) {
            throw new IllegalArgumentException("포인트가 모자랍니다.");
        }
    }

    private void calculateUsePoint(int usePoint, User user, List<PointUser> pointUsers) {
        for (PointUser pointUser : pointUsers) {
            int remainPoint = usePoint - pointUser.getPoint();

            pointUser.statusUpdate(PointUserStatus.USE.getName());

            if (remainPoint < 0) {
                String status = PointUserStatus.ACCUMULATE.getName();
                String type = PointUserType.REMAIN_POINT.getName();
                LocalDateTime expiredDate = pointUser.getExpiredDate();

                pointUserService.save(user, status, type, -remainPoint, expiredDate);
                return;
            }

            usePoint = remainPoint;
        }
    }
}


