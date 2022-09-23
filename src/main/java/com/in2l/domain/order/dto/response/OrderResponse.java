package com.in2l.domain.order.dto.response;

import com.in2l.domain.order.domain.Order;
import com.in2l.domain.order.domain.OrderStatus;
import com.in2l.domain.product.dto.response.ProductResponseDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderResponse {

  @Id
  private Long id;

  private List<ProductResponseDto> productResponseDtos = new ArrayList<>();

  private Long memberId;   //user의 PK

  private Long shopId;   //shop의 PK임.

  private String MESSAGE;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @Builder
  public OrderResponse(Long id,
      List<ProductResponseDto> productResponseDtos, Long memberId, Long shopId,
      String MESSAGE, OrderStatus orderStatus) {
    this.id = id;
    this.productResponseDtos = productResponseDtos;
    this.memberId = memberId;
    this.shopId = shopId;
    this.MESSAGE = MESSAGE;
    this.orderStatus = orderStatus;
  }

  public OrderResponse setMESSAGE(String MESSAGE) {
    this.MESSAGE = MESSAGE;
    return null;
  }

  public static OrderResponse of(Order order) {
    OrderResponse orderResponse = OrderResponse.builder()
        .id(order.getId())
        .memberId(order.getId())
        .shopId(order.getShopId())
        .orderStatus(order.getOrderStatus())
        .build();

    return orderResponse;
  }
}
