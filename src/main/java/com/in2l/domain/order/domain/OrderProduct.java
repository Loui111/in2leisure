package com.in2l.domain.order.domain;

import com.in2l.domain.product.domain.Product;
import com.in2l.global.common.domain.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Table(name = "order_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert    //deleteFlat의 default값을 제대로 넣으려면 이게 선언되어 있어야함.
public class OrderProduct extends BaseTimeEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "orders")    //order_id?
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product")    //product_id???
  private Product product;

  private Long buyCount;

  private Long productPrice;

  @Column(columnDefinition= "bit default false")
  private boolean deleteFlag;     //TODO: 근데 왠지 delete바뀐 날짜는 어딘가 박아 넣어야 할듯? (개인정보이슈?)

  @Builder
  public OrderProduct(Order order, Product product, Long buyCount, Long productPrice,
      boolean deleteFlag) {
    this.order = order;
    this.product = product;
    this.buyCount = buyCount;
    this.productPrice = productPrice;
    this.deleteFlag = deleteFlag;
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
