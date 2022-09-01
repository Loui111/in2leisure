package com.in2l.domain.orders.controller;

import com.in2l.domain.member.dto.request.MemberRequest;
import com.in2l.domain.member.dto.response.MemberResponse;
import com.in2l.domain.orders.domain.OrderStatus;
import com.in2l.domain.orders.domain.Orders;
import com.in2l.domain.orders.dto.request.OrdersRequest;
import com.in2l.domain.orders.dto.response.OrdersResponse;
import com.in2l.domain.orders.service.OrdersService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/v1/orders")
public class OrdersController {

  private final OrdersService ordersService;

  @GetMapping("/{ordersId}")
  public Orders getOrders(@PathVariable Long ordersId){
    return ordersService.selectOrders(ordersId);
  }

  @PostMapping("")   //order 등록 == 누군가 구매를 진행한다.
  public OrdersResponse postOrders(@RequestBody OrdersRequest ordersRequest){
    return ordersService.createOrders(ordersRequest);

  }

//  @PatchMapping("/{ordersId}")
//  public OrdersResponse patchOrders(@RequestBody @Valid OrdersRequest ordersRequest,
//      @PathVariable Long ordersId) {
//
//    return ordersService.updateOrders(memberRequest, memberId);
//  }
//
//  @DeleteMapping("/{ordersId}")
//  public MemberResponse deleteOrders(@PathVariable Long orderId) {
//    return memberService.removeMember(memberId);
//  }

}
