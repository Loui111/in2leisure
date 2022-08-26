package com.in2l.domain.product.domain;

import static javax.persistence.FetchType.LAZY;

import com.in2l.domain.order.domain.Order;
import com.in2l.global.common.domain.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product")
public class Product {

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

  @Id
  @Column(name = "product_id")
  @GeneratedValue
  private Long product_id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

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
}
