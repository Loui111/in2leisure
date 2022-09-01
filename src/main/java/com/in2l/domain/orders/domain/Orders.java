package com.in2l.domain.orders.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in2l.domain.orders.dto.request.OrdersRequest;
import com.in2l.domain.product.domain.Product;
import com.in2l.global.common.domain.BaseTimeEntity;
import com.in2l.global.common.domain.Currency;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
@Table(name = "orders")   //'order'는 RDB예약어라 쓸수 없음.
public class Orders extends BaseTimeEntity {

  /** + productList
   * member_id        :Long
   * memberName       :String
   * shop_id        :Long
   * shopName     :String
   * originPrice    :Long
   * discountPrice: Long
   * discountRate : float
   * currency        :ENUM
   * orderStatus    : ENUM
   */

  @Id
  @GeneratedValue
  @Column(name = "orders_id")
  private Long orders_id;

  @JsonIgnore
  @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
  private List<Product> productList = new ArrayList<>();

  private Long member_id;   //user의 PK

  private String memberName;

  private Long shop_id;   //shop의 PK임.

  private String shopName;

  private Long originPrice;

  private Long discountPrice;

  private float discountRate;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @Builder
  public Orders(Long member_id, String memberName, Long shop_id, String shopName,
      Long originPrice, Long discountPrice, float discountRate,
      Currency currency, OrderStatus orderStatus) {
    this.member_id = member_id;
    this.memberName = memberName;
    this.shop_id = shop_id;
    this.shopName = shopName;
    this.originPrice = originPrice;
    this.discountPrice = discountPrice;
    this.discountRate = discountRate;
    this.currency = currency;
    this.orderStatus = orderStatus;
  }

//  public static Orders createOrders(OrdersRequest ordersRequest){
//
//
//  }

  public void putProduct(Product product){
    this.productList.add(product);
  }
}
