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
   * memberName       :String
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
  private Long member_id;   //user의 PK

  private String memberName;

  private List<ProductRequestDto> productList;

  private Long shop_id;   //shop의 PK임.

  private String shopName;

  private Long originPrice;

  private Long discountPrice;

  private float discountRate;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  //TODO: 없어야 함. ??먼소리야? 왜 없어야해? Getter라서?
//  public List<ProductRequestDto> getProductList(){
//    return productList;
//  }

  @Builder
  public OrderRequest(@NotBlank Long member_id, String memberName,
      List<ProductRequestDto> productList, Long shop_id, String shopName, Long originPrice,
      Long discountPrice, float discountRate, Currency currency,
      OrderStatus orderStatus) {
    this.member_id = member_id;
    this.memberName = memberName;
    this.productList = productList;
    this.shop_id = shop_id;
    this.shopName = shopName;
    this.originPrice = originPrice;
    this.discountPrice = discountPrice;
    this.discountRate = discountRate;
    this.currency = currency;
    this.orderStatus = orderStatus;
  }
}

