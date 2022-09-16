package com.in2l.domain.order.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderProductRepositoryImpl implements OrdersProductRepositoryCustom{

  private final JPAQueryFactory jpaQueryFactory;
}
