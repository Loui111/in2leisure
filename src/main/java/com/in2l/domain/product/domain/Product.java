package com.in2l.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in2l.domain.order.domain.OrderProduct;
import com.in2l.global.common.domain.BaseTimeEntity;
import com.in2l.global.common.domain.Currency;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
//  @Column(name = "product_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 데이터베이스에 위임
  private Long id;

//  @JsonIgnore       //orderItems 에서 보고 있으므로 여기선 볼 필요가 없다.
//  @ManyToOne(fetch = LAZY)
//  @JoinColumn(name = "orders_id")
//  private Orders order;

  @JsonIgnore
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private List<OrderProduct> orderProducts = new ArrayList<>();

  private Long shopId;     //shop PK

  private String shopName;

  private String productName;

  private String productDesc;

  private Long amount;

  private Long originPrice;

  private Long discountPrice;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  private Long soldCount;

  private boolean productDelete;

  @Builder
  public Product(List<OrderProduct> orderProducts, Long shopId, String shopName,
      String productName, String productDesc, Long amount, Long originPrice,
      Long discountPrice, Currency currency, Long soldCount, boolean productDelete) {
    this.orderProducts = orderProducts;
    this.shopId = shopId;
    this.shopName = shopName;
    this.productName = productName;
    this.productDesc = productDesc;
    this.amount = amount;
    this.originPrice = originPrice;
    this.discountPrice = discountPrice;
    this.currency = currency;
    this.soldCount = soldCount;
    this.productDelete = productDelete;
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

//  public static Product createProduct(ProductRequestDto productRequestDto, Orders order){
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
