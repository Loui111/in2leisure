package com.in2l.domain.order.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrdersRepositoryCustom{

  private final JPAQueryFactory jpaQueryFactory;
}
