package tenshop.core.order.repository;

import static tenshop.core.order.QOrder.*;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tenshop.config.querydsl.QuerydslRepositorySupport;
import tenshop.core.order.Order;
import tenshop.core.order.QOrder;
import tenshop.core.users.QUsers;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom {

    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public Page<Order> findByUserId(Long userId, Pageable pageable) {
        return applyPagination(pageable,
                contentQuery -> contentQuery
                        .select(order)
                        .from(order)
                        .where(
                                userIdEq(userId)
                        )
                , countQuery -> countQuery
                        .select(order.count())
                        .from(order)
                        .where(
                                userIdEq(userId)
                        )
                );
    }

    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? QUsers.users.id.eq(userId) : null;
    }
}


