package com.in2l.domain.orders.dto.response;

import com.in2l.domain.orders.domain.OrderStatus;
import com.in2l.domain.orders.domain.OrdersProduct;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrdersResponse {
  /**
   * user_id        :Long
   * shop_id        :Long
   * shopName     :String
   * originPrice    :Long
   * discountPrice: Long
   * discountRate : float
   * currency        :ENUM
   * orderStatus    : ENUM
   */

  @Id
  private Long orders_id;

  //  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
  private List<OrdersProduct> ordersProductList = new ArrayList<>();

  private Long user_id;   //user의 PK

  private Long shop_id;   //shop의 PK임.

  //이 아래 정보들은 굳이 필요 없을거 같은데?
//  private String shopName;
//
//  private Long originPrice;
//
//  private Long discountPrice;
//
//  private float discountRate;
//
//  @Enumerated(EnumType.STRING)
//  private Currency currency;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @Builder
  public OrdersResponse(Long orders_id,
      List<OrdersProduct> ordersProductList, Long user_id, Long shop_id,
      OrderStatus orderStatus) {
    this.orders_id = orders_id;
    this.ordersProductList = ordersProductList;
    this.user_id = user_id;
    this.shop_id = shop_id;
    this.orderStatus = orderStatus;
  }
}
