package com.in2l.domain.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in2l.global.common.domain.Currency;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderEditor{

  //TODO: OrderEditor에 당연 products 도 들어가겠지.
  private List<OrderProduct> orderProducts = new ArrayList<>();

  //member수정은 원칙적으로 안되야 할듯.
  private Long memberId;   //user의 PK

  private String memberName;

  private Long shopId;   //shop의 PK임.

  private String shopName;

  private Long originPrice;

  private Long discountPrice;

  private float discountRate;

  @Column(columnDefinition= "bit default false")
//  @NotBlank   TODO: 이건 null 이면 안되고, 디폴트로 false로 넣어야 한다는건데 이런게 가능?
  private boolean deleteFlag;     //TODO: 근데 왠지 delete바뀐 날짜는 어딘가 박아 넣어야 할듯? (개인정보이슈?)

  @Enumerated(EnumType.STRING)
  private Currency currency;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @Builder
  public OrderEditor(List<OrderProduct> orderProducts, Long memberId, String memberName,
      Long shopId, String shopName, Long originPrice, Long discountPrice, float discountRate,
      boolean deleteFlag, Currency currency, OrderStatus orderStatus) {
    this.orderProducts = orderProducts;
    this.memberId = memberId;
    this.memberName = memberName;
    this.shopId = shopId;
    this.shopName = shopName;
    this.originPrice = originPrice;
    this.discountPrice = discountPrice;
    this.discountRate = discountRate;
    this.deleteFlag = deleteFlag;
    this.currency = currency;
    this.orderStatus = orderStatus;
  }

  public static OrderEditor of(OrderEditorBuilder orderEditorBuilder, Order originOrder){
    return orderEditorBuilder
//        .orderProducts(originOrder.getOrderProducts())
        .memberId(originOrder.getMemberId())
        .memberName(originOrder.getMemberName())
        .shopId(originOrder.getShopId())
        .shopName(originOrder.getShopName())
        .originPrice(originOrder.getOriginPrice())
        .discountPrice(originOrder.getDiscountPrice())
        .discountRate(originOrder.getDiscountRate())
        .deleteFlag(originOrder.isDeleteFlag())
        .currency(originOrder.getCurrency())
        .orderStatus(originOrder.getOrderStatus())
        .build();
  }

}
