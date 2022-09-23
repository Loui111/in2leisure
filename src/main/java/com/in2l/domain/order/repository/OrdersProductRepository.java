package com.in2l.domain.order.repository;

import com.in2l.domain.order.domain.OrderProduct;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrdersProductRepository extends JpaRepository<OrderProduct, Long>, OrdersProductRepositoryCustom {


}
