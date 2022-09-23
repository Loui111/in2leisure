package com.in2l.domain.order.dto.request;

import com.in2l.domain.order.domain.OrderStatus;
import com.in2l.domain.product.domain.Product;
import com.in2l.domain.product.dto.request.ProductRequestDto;
import com.in2l.global.common.domain.Currency;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequest {

  /**
   * member_id        :Long
   * name       :String
   * shop_id        :Long
   * shopName     :String
   * originPrice    :Long
   * discountPrice: Long
   * discountRate : float
   * currency        :ENUM
   * orderStatus    : ENUM
   */

//  private List<ProductRequestDto> productList = new ArrayList<>();
  @NotBlank
  private Long memberId;   //user의 PK

  private String memberName;

  private List<ProductRequestDto> products;

  private Long shopId;   //shop의 PK임.

  private String shopName;

  private Long originPrice;

  private Long discountPrice;

  private float discountRate;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @Builder
  public OrderRequest(@NotBlank Long memberId, String memberName,
      List<ProductRequestDto> products, Long shopId, String shopName, Long originPrice,
      Long discountPrice, float discountRate, Currency currency,
      OrderStatus orderStatus) {
    this.memberId = memberId;
    this.memberName = memberName;
    this.products = products;
    this.shopId = shopId;
    this.shopName = shopName;
    this.originPrice = originPrice;
    this.discountPrice = discountPrice;
    this.discountRate = discountRate;
    this.currency = currency;
    this.orderStatus = orderStatus;
  }
}

