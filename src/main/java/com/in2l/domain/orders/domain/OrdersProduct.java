package com.in2l.domain.orders.domain;

import com.in2l.domain.product.domain.Product;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "orders_product")
public class OrdersProduct {

  /**
   * orders_product_id
   * orders     :List
   * product    :List
   */

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "orders_product_id")
  private Long orders_product_id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "orders_id")
  private Orders orders;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  @Builder
  public OrdersProduct(Orders orders, Product product) {
    this.orders = orders;
    this.product = product;
  }

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
