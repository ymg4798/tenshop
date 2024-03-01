package tenshop.core.order.repository;

import static tenshop.core.order.QOrder.*;
import static tenshop.core.order.domain.QOrderProducts.*;
import static tenshop.core.product.QProduct.*;
import static tenshop.core.users.QUsers.*;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import tenshop.config.querydsl.QuerydslRepositorySupport;
import tenshop.core.order.Order;
import tenshop.core.order.QOrder;
import tenshop.core.order.domain.QOrderProducts;
import tenshop.core.order.dto.OrderListResponse;
import tenshop.core.order.dto.OrderResponse;
import tenshop.core.order.dto.QOrderListResponse;
import tenshop.core.order.dto.QOrderResponse;
import tenshop.core.product.QProduct;
import tenshop.core.users.QUsers;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements
        OrderRepositoryCustom {


    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<OrderResponse> findByOrderId(Long orderId) {
        return getQueryFactory()
                .select(new QOrderResponse(orderProducts.id, product.name, orderProducts.status,
                        orderProducts.quantity, product.price))
                .from(orderProducts)
                .join(product).on(orderProducts.productId.eq(product.id))
                .where(orderProducts.order.id.eq(orderId))
                .fetch();
    }

    @Override
    public Page<OrderListResponse> findOrders(Long userId, Pageable pageable) {
        /*QueryResults<OrderListResponse> results = select(
                new QOrderListResponse(order.id, order.address, order.paymentPrice,
                        order.usePoint, order.status, order.createdDate))
                .from(order)
                .where(order.userId.eq(userId))
                .offset(pageable.getOffset()) *//*offset*//*
                .limit(pageable.getPageSize())*//*limit*//*
                .fetchResults();

        List<OrderListResponse> results1 = results.getResults();
        return new PageImpl<>(results1,pageable,results.getTotal());*/

        return applyPagination(pageable,
                contentQuery -> contentQuery
                        .select(new QOrderListResponse(order.id, order.address, order.paymentPrice,
                                order.usePoint, order.status, order.createdDate))
                        .from(order)
                        .where(order.userId.eq(userId))
                , countQuery -> countQuery
                        .select(order.count())
                        .from(order)
                        .where(order.userId.eq(userId))
        );

    }

    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? users.id.eq(userId) : null;
    }
}


