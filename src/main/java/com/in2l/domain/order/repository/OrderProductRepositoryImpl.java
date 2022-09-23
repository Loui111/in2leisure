package com.in2l.domain.order.repository;

import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.domain.QMember;
import com.in2l.domain.order.domain.OrderProduct;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class OrderProductRepositoryImpl implements OrdersProductRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<OrderProduct> findByOrder_id(Long order_id) {
    return null;

//    return jpaQueryFactory.selectFrom(QOrderProduct.orderProduct)
//        .where(QOrderProduct.orderProduct.order.order_id.eq(order_id))
//        .fetch();
  }

//  @Override
//  public List<Member> findByName(String name) {
//    return jpaQueryFactory.selectFrom(QMember.member)
//        .where(QMember.member.name.eq(name))
//        .fetch();
//  }

//  @Override
//  public List<OrderProduct> findByOrderProduct(Long order_id) {
//    return jpaQueryFactory.selectFrom(QOrderProduct.orderProduct)
//        .where(QOrderProduct.orderProduct.order.order_id.eq(order_id))
//        .fetch();
//  }
}

//       return jpaQueryFactory.selectFrom(QMember.member)
//      .where(QMember.member.name.eq(name))
//      .fetch();

//  @Override
//  public List<OrderProduct> findByOrderProduct(Long order_id) {
//
//    return null;
//
////    return queryFactory
////        .selectFrom(orderProduct)
////        .where(orderProduct.order.order_id.eq(order_id))
////        .fetch();
//  }




//    return from(member)
//        .leftJoin(member.team, team)
//        .where(
//            usernameEq(condition.getUsername()),
//            teamNameEq(condition.getTeamName()),
//            ageGoe(condition.getAgeGoe()),
//            ageLoe(condition.getAgeLoe())
//        )
//        .select(new QMemberTeamDto(
//            member.id.as("id"),
//            member.username,
//            member.age,
//            team.id.as("teamId"),
//            team.name.as("teamName")
//        ))
//        .fetch();









