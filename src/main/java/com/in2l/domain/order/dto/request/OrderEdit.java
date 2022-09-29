package com.in2l.domain.order.dto.request;

import com.in2l.domain.order.domain.OrderStatus;
import com.in2l.domain.product.dto.request.ProductRequestDto;
import com.in2l.global.common.domain.Currency;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderEdit {

  private Long id;

  private Long memberId;   //user의 PK

  private String memberName;

  private List<ProductRequestDto> products;

  private Long shopId;   //shop의 PK임.

  private String shopName;

  private Long originPrice;

  private Long discountPrice;

  private float discountRate;

  private boolean deleteFlag;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @Builder
  public OrderEdit(Long id, Long memberId, String memberName,
      List<ProductRequestDto> products, Long shopId, String shopName, Long originPrice,
      Long discountPrice, float discountRate, boolean deleteFlag,
      Currency currency, OrderStatus orderStatus) {
    this.id = id;
    this.memberId = memberId;
    this.memberName = memberName;
    this.products = products;
    this.shopId = shopId;
    this.shopName = shopName;
    this.originPrice = originPrice;
    this.discountPrice = discountPrice;
    this.discountRate = discountRate;
    this.deleteFlag = deleteFlag;
    this.currency = currency;
    this.orderStatus = orderStatus;
  }

  public static OrderEdit of(OrderRequest orderRequest) {
    return OrderEdit.builder()
        .memberId(orderRequest.getMemberId())
        .memberName(orderRequest.getMemberName())
//        .products(orderRequest.getProducts())
        .shopId(orderRequest.getShopId())
        .shopName(orderRequest.getShopName())
        .originPrice(orderRequest.getOriginPrice())
        .discountPrice(orderRequest.getDiscountPrice())
        .discountRate(orderRequest.getDiscountRate())
        .currency(orderRequest.getCurrency())
        .orderStatus(orderRequest.getOrderStatus())
        .deleteFlag(orderRequest.isDeleteFlag())
        .build();
  }
}
