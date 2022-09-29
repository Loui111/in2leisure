package com.in2l.domain.product.dto.response;

import com.in2l.domain.product.dto.request.ProductRequestDto;
import com.in2l.global.common.domain.Currency;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponseDto {

  private Long productId;

  private String productName;

  private Long buyCount;

  @Builder
  public ProductResponseDto(Long productId, String productName, Long buyCount) {
    this.productId = productId;
    this.productName = productName;
    this.buyCount = buyCount;
  }

  public static ProductResponseDto of(ProductRequestDto p) {
    return ProductResponseDto.builder()
        .productId(p.getProductId())
        .productName(p.getProductName())
        .buyCount(p.getBuyCount())
        .build();
  }

}
