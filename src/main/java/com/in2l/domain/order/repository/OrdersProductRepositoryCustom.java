package com.in2l.domain.order.repository;

import com.in2l.domain.member.domain.Member;
import com.in2l.domain.order.domain.OrderProduct;
import java.util.List;

public interface OrdersProductRepositoryCustom {
  List<OrderProduct> findByOrder_id(Long order_id);


//  List<OrderProduct> findByOrderProduct(Long order_id);

//  List<OrderProduct> search(Long order_id);

//  List<MemberTeamDto> search(MemberSearchCondition condition);
//  Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable);
//  Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable);

}
