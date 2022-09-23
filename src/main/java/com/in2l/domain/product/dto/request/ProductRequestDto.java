package com.in2l.domain.product.dto.request;

import com.in2l.global.common.domain.Currency;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequestDto {
  private Long productId;

  private String productName;

  private String productDesc;

  private Long buyCount;

  private Long originPrice;

  private Long discountPrice;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  @Builder
  public ProductRequestDto(Long productId, String productName, String productDesc,
      Long buyCount, Long originPrice, Long discountPrice,
      Currency currency) {
    this.productId = productId;
    this.productName = productName;
    this.productDesc = productDesc;
    this.buyCount = buyCount;
    this.originPrice = originPrice;
    this.discountPrice = discountPrice;
    this.currency = currency;
  }
}
