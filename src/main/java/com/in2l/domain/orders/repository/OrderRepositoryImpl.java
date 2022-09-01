package com.in2l.domain.orders.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrdersRepositoryCustom{

  private final JPAQueryFactory jpaQueryFactory;
}
