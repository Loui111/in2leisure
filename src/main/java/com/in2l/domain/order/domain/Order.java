package com.in2l.domain.order.domain;

import com.in2l.domain.product.domain.Product;
import com.in2l.global.common.domain.Currency;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

  /**
   * shop_id        :Long
   * optionName  :String
   * originPrice    :Long
   * discountPrice: Long
   * discountRate : float
   * currency        :ENUM
   * shopName     :String
   * orderStatus    : ENUM
   */

  @Id
  @Column(name = "order_id")
  @GeneratedValue
  private Long order_id;

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
  private List<Product> productList = new ArrayList<>();

  private Long shop_id;   //shop의 PK임.

  private String optionName;

  private Long originPrice;

  private Long discountPrice;

  private float discountRate;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  private String shopName;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;
}
