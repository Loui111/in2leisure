package com.in2l.domain.product.domain;

import static javax.persistence.FetchType.LAZY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in2l.domain.orders.domain.Orders;
import com.in2l.domain.product.dto.request.ProductRequestDto;
import com.in2l.global.common.domain.BaseTimeEntity;
import com.in2l.global.common.domain.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class Product extends BaseTimeEntity {

  /**
   * shop_id    : Long
   * shopName       :String
   * productName   :String
   * productDesc     :String
   * amount           :Long
   * originPrice      :Long
   * discountPrice  :Long
   * currency         :ENUM
   * soldCount        :Long
   */

  @Id
  @Column(name = "product_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 데이터베이스에 위임
  private Long product_id;

//  @JsonIgnore       //orderItems 에서 보고 있으므로 여기선 볼 필요가 없다.
//  @ManyToOne(fetch = LAZY)
//  @JoinColumn(name = "orders_id")
//  private Orders orders;

  private Long shop_id;     //shop PK

  private String shopName;

  private String productName;

  private String productDesc;

  private Long amount;

  private Long originPrice;

  private Long discountPrice;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  private Long soldCount;

  @Builder
  public Product(Long shop_id, String shopName, String productName, String productDesc,
      Long amount, Long originPrice, Long discountPrice,
      Currency currency, Long soldCount) {
    this.shop_id = shop_id;
    this.shopName = shopName;
    this.productName = productName;
    this.productDesc = productDesc;
    this.amount = amount;
    this.originPrice = originPrice;
    this.discountPrice = discountPrice;
    this.currency = currency;
    this.soldCount = soldCount;
  }
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

//  public static Product createProduct(ProductRequestDto productRequestDto, Orders orders){
//    return Product.builder()
//        .productName(productRequestDto.getProductName())
//        .productDesc(productRequestDto.getProductDesc())
//        .amount(productRequestDto.getAmount())
//        .originPrice(productRequestDto.getOriginPrice())
//        .discountPrice(productRequestDto.getDiscountPrice())
//        .currency(productRequestDto.getCurrency())
//        .amount(productRequestDto.getAmount())
//        .build();
//  }
}
