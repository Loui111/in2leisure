package com.in2l.domain.order.domain;

import com.in2l.domain.product.domain.Product;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "order_product")
public class OrderProduct {

  /**
   * orders_product_id order     :List product    :List
   */

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "order_product_id")   //TODO: 이게 필요한가? 매핑테이블인데?
  private Long order_product_id;

  ////  @ManyToOne(fetch = FetchType.LAZY)
  @ManyToOne()
  @JoinColumn(name = "order_id")
  private Order order;

  //  @ManyToOne(fetch = FetchType.LAZY)
  @ManyToOne()
  @JoinColumn(name = "product_id")
  private Product product;

  @Builder
  public OrderProduct(Order order, Product product) {
    this.order = order;
    this.product = product;
  }

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
