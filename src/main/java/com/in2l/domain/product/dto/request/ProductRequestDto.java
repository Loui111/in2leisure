package com.in2l.domain.product.dto.request;

import static javax.persistence.FetchType.LAZY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in2l.domain.orders.domain.Orders;
import com.in2l.global.common.domain.Currency;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequestDto {

  /**
   * shopName    : String
   * productName   :String
   * productDesc     :String
   * amount           :Long
   * originPrice      :Long
   * discountPrice  :Long
   * currency         :ENUM
   * soldCount        :Long
   */

//  private Long shop_id;     //shop PK
//
//  private String shopName;

  private String productName;

  private String productDesc;

  private Long amount;

  private Long originPrice;

  private Long discountPrice;

  @Enumerated(EnumType.STRING)
  private Currency currency;

//  private Long soldCount;
}
