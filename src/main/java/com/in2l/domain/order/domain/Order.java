package com.in2l.domain.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "orders")   //'order'는 RDB예약어라 쓸수 없음.
public class Order extends BaseTimeEntity {

  /**
   * member_id        :Long
   * shop_id        :Long
   * shopName     :String
   * originPrice    :Long
   * discountPrice: Long
   * discountRate : float
   * currency        :ENUM
   * orderStatus    : ENUM
   */

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long order_id;

  @JsonIgnore
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderProduct> orderProductList = new ArrayList<>();

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
  public Order(List<OrderProduct> orderProductList, Long member_id, String memberName,
      Long shop_id, String shopName, Long originPrice, Long discountPrice, float discountRate,
      Currency currency, OrderStatus orderStatus) {
    this.orderProductList = orderProductList;
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
//  }

  public void putOrderItems(OrderProduct orderProduct){
    this.orderProductList.add(orderProduct);
  }
}
