package tenshop.core.users.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tenshop.config.auditing.BaseTimeEntity;
import tenshop.core.users.User;
import tenshop.core.users.domain.converter.PointUserStatusConverter;
import tenshop.core.users.domain.converter.PointUserTypeConverter;
import tenshop.core.users.domain.converter.enums.PointUserStatus;
import tenshop.core.users.domain.converter.enums.PointUserType;

import java.time.LocalDateTime;

import static tenshop.config.converter.EnumConverterUtils.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PointUser extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Convert(converter = PointUserStatusConverter.class)
    @Column(columnDefinition = "varchar(20)")
    private PointUserStatus status;

    @Convert(converter = PointUserTypeConverter.class)
    @Column(columnDefinition = "varchar(20)")
    private PointUserType type;

    private int point;

    private LocalDateTime expiredDate;

    @Builder
    public PointUser(User user, PointUserStatus status, PointUserType type, int point, LocalDateTime expiredDate) {
        this.user = user;
        this.status = status;
        this.type = type;
        this.point = point;
        this.expiredDate = expiredDate;
    }

    public void setUser(User user) {
        this.user = user;
        user.getPointUsers().add(this);
    }

    public static PointUser create(User user, String status, String type, int point, LocalDateTime expiredDate) {
        PointUser pointUser = PointUser.builder()
                .user(user)
                .status(ofName(PointUserStatus.class, status))
                .type(ofName(PointUserType.class, type))
                .point(point)
                .expiredDate(expiredDate)
                .build();
        pointUser.setUser(user);
        return pointUser;
    }

    public void statusUpdate(String status) {
        this.status = ofName(PointUserStatus.class, status);
    }
}


