package com.in2l.domain.order.repository;

import com.in2l.domain.order.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersProductRepository extends JpaRepository<OrderProduct, Long>, OrdersProductRepositoryCustom {

}


