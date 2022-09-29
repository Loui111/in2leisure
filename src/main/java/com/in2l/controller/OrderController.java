package com.in2l.controller;

import com.in2l.domain.order.dto.request.OrderRequest;
import com.in2l.domain.order.dto.response.OrderResponse;
import com.in2l.domain.order.service.OrderService;
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
@RequestMapping(value = "${apiVersion}/{shopId}/order")   //TODO: /v0/{category}/{subCategory}/{shopId} 이렇게 해야 할듯?
public class OrderController {

  private final OrderService orderService;

  @GetMapping("/{id}")
  public Object getOrder(@PathVariable Long id){
    return orderService.getOrder(id);
  }

  @PostMapping("")
  public Object postOrder(@RequestBody OrderRequest orderRequest){
    OrderResponse ordersResponse = orderService.postOrder(orderRequest);
    return ordersResponse;
  }

  @PatchMapping("{id}")   //delete도 이걸로 씀.
  public Object patchOrder(@PathVariable Long id, @RequestBody @Valid OrderRequest orderRequest){
    return orderService.patchOrder(orderRequest, id);
  }

  @DeleteMapping("/{id}")     //delete는 안쓸거임.
  public void deleteOrder(@PathVariable Long id){
    orderService.deleteOrder(id);
  }
}
