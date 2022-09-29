package com.in2l.domain.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in2l.domain.order.domain.OrderEditor.OrderEditorBuilder;
import com.in2l.domain.order.dto.request.OrderEdit;
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
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")   //'order'는 RDB예약어라 쓸수 없음.
@DynamicInsert    //deleteFlat의 default값을 제대로 넣으려면 이게 선언되어 있어야함.
public class Order extends BaseTimeEntity {

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

  @Column(columnDefinition= "bit default false")
//  @NotBlank   TODO: 이건 null 이면 안되고, 디폴트로 false로 넣어야 한다는건데 이런게 가능?
  private boolean deleteFlag;     //TODO: 근데 왠지 delete바뀐 날짜는 어딘가 박아 넣어야 할듯? (개인정보이슈?)

  @Enumerated(EnumType.STRING)
  private Currency currency;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @Builder
  public Order(List<OrderProduct> orderProducts, Long memberId, String memberName,
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
//        .deleteFlag(orderRequest.ge)
        .build();
  }

//  public static Orders createOrders(OrdersRequest ordersRequest){
//  }

  public void putOrderItems(OrderProduct orderProduct){
    this.orderProducts.add(orderProduct);
  }

  public OrderEditor.OrderEditorBuilder toEditor() {
    OrderEditor.OrderEditorBuilder builder = OrderEditor.builder()
//        .orderProducts(orderProducts)
        .memberId(memberId)
        .memberName(memberName)
        .shopId(shopId)
        .shopName(shopName)
        .originPrice(originPrice)
        .discountPrice(discountPrice)
        .discountRate(discountRate)
        .currency(currency)
        .deleteFlag(deleteFlag)
        .orderStatus(orderStatus);

    return builder;
  }

  public void edit(OrderEdit orderEdit) {
//    orderProducts = orderEdit.getProducts();  //이거 넣으려면 orderProducts.of(orderProductRequest)를 만들어야함. 귀찮..
    memberId = orderEdit.getMemberId();
    memberName = orderEdit.getMemberName();
    shopId = orderEdit.getShopId();
    shopName = orderEdit.getShopName();
    originPrice = orderEdit.getOriginPrice();
    discountPrice = orderEdit.getDiscountPrice();
    discountRate = orderEdit.getDiscountRate();
    currency = orderEdit.getCurrency();
    orderStatus = orderEdit.getOrderStatus();
    deleteFlag = orderEdit.isDeleteFlag();
  }
}
