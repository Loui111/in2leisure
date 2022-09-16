package com.in2l.domain.order.repository;

import com.in2l.domain.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long>, OrdersRepositoryCustom {

}


