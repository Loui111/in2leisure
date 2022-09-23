package com.in2l.domain.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in2l.domain.order.dto.request.OrderRequest;
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
//  @Column(name = "id")
  private Long id;

  @JsonIgnore
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderProduct> orderProducts = new ArrayList<>();

  private Long memberId;   //user의 PK

  private String memberName;

  private Long shopId;   //shop의 PK임.

  private String shopName;

  private Long originPrice;

  private Long discountPrice;

  private float discountRate;

  private boolean orderDelete;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;


  @Builder
  public Order(List<OrderProduct> orderProducts, Long memberId, String memberName,
      Long shopId, String shopName, Long originPrice, Long discountPrice, float discountRate,
      boolean orderDelete, Currency currency, OrderStatus orderStatus) {
    this.orderProducts = orderProducts;
    this.memberId = memberId;
    this.memberName = memberName;
    this.shopId = shopId;
    this.shopName = shopName;
    this.originPrice = originPrice;
    this.discountPrice = discountPrice;
    this.discountRate = discountRate;
    this.orderDelete = orderDelete;
    this.currency = currency;
    this.orderStatus = orderStatus;
  }

  public static Order of(OrderRequest orderRequest) {   //여기 products가 들어가야하지 않나?
    return Order.builder()
        .memberId(orderRequest.getMemberId())
        .memberName(orderRequest.getMemberName())
        .shopId(orderRequest.getShopId())
        .shopName(orderRequest.getShopName())
        .originPrice(orderRequest.getOriginPrice())
        .discountPrice(orderRequest.getDiscountPrice())
        .discountRate(orderRequest.getDiscountRate())
        .currency(orderRequest.getCurrency())
        .orderStatus(orderRequest.getOrderStatus())
        .build();
  }

//  public static Orders createOrders(OrdersRequest ordersRequest){
//  }

  public void putOrderItems(OrderProduct orderProduct){
    this.orderProducts.add(orderProduct);
  }
}
