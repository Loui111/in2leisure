package com.in2l.domain.order.dto.response;

import com.in2l.domain.order.domain.Order;
import com.in2l.domain.order.domain.OrderStatus;
import com.in2l.domain.product.dto.response.ProductResponseDto;
import com.in2l.domain.product.dto.request.ProductRequestDto;
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
  private Long order_id;

  //TODO: productResponseDTO까지 변환이 귀찮아서 뺏는데 이거 나중엔 구현할까?
//  private List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

  private Long member_id;   //user의 PK

  private Long shop_id;   //shop의 PK임.

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @Builder
  public OrderResponse(Long order_id, Long member_id, Long shop_id,
      OrderStatus orderStatus) {
    this.order_id = order_id;
    this.member_id = member_id;
    this.shop_id = shop_id;
    this.orderStatus = orderStatus;
  }

  public static OrderResponse of(Order order) {
    OrderResponse orderResponse = OrderResponse.builder()
        .order_id(order.getOrder_id())
        .member_id(order.getMember_id())
        .shop_id(order.getShop_id())
        .orderStatus(order.getOrderStatus())
//        .productResponseDtoList(order.getOrderProductList())
        .build();

    return orderResponse;
  }
}
