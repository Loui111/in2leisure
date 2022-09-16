package com.in2l.controller;

import com.in2l.domain.order.domain.Order;
import com.in2l.domain.order.dto.request.OrderRequest;
import com.in2l.domain.order.dto.response.OrderResponse;
import com.in2l.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/v0/{category}/{subCategory}")
public class ShopController {
  //Home-Type-subType-Detail

//  private final OrderService orderService;
////
//  @GetMapping("/{ordersId}")
//  public OrderResponse getOrder(@PathVariable Long ordersId){
//    return orderService.getOrder(ordersId);
//  }
//
//
//  @PostMapping("")   //order 등록 == 누군가 구매를 진행한다.
////  public OrdersResponse postOrders(@RequestBody OrdersRequest ordersRequest){
//  public OrderResponse postOrder(@RequestBody OrderRequest orderRequest){
//    OrderResponse ordersResponse = orderService.postOrder(orderRequest);
//    return ordersResponse;
//
//  }

}
