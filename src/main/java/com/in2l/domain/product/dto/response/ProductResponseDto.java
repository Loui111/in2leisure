package com.in2l.domain.product.dto.response;

import com.in2l.global.common.domain.Currency;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponseDto {

  private Long product_id;

  private String productName;

  private Long buyCount;

  @Builder
  public ProductResponseDto(Long product_id, String productName, Long buyCount) {
    this.product_id = product_id;
    this.productName = productName;
    this.buyCount = buyCount;
  }

}
