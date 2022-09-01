package com.in2l.domain.orders.repository;

import com.in2l.domain.orders.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long>, OrdersRepositoryCustom {

}


