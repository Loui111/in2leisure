package com.in2l.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in2l.domain.order.domain.OrderProduct;
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
@Table(name = "product")
@DynamicInsert    //deleteFlat의 default값을 제대로 넣으려면 이게 선언되어 있어야함.
public class Product extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 데이터베이스에 위임
  private Long id;

  @JsonIgnore
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private List<OrderProduct> orderProducts = new ArrayList<>();

  private Long shopId;     //shop PK

  private String shopName;

  private String productName;

  private String productDesc;

  private Long amount;

  private Long originPrice;

  private Long discountPrice;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  private Long soldCount;

  @Column(columnDefinition= "bit default false")
//  @NotBlank   TODO: 이건 null 이면 안되고, 디폴트로 false로 넣어야 한다는건데 이런게 가능?
  private boolean deleteFlag;     //TODO: 근데 왠지 delete바뀐 날짜는 어딘가 박아 넣어야 할듯? (개인정보이슈?)

  @Builder
  public Product(List<OrderProduct> orderProducts, Long shopId, String shopName,
      String productName, String productDesc, Long amount, Long originPrice,
      Long discountPrice, Currency currency, Long soldCount, boolean deleteFlag) {
    this.orderProducts = orderProducts;
    this.shopId = shopId;
    this.shopName = shopName;
    this.productName = productName;
    this.productDesc = productDesc;
    this.amount = amount;
    this.originPrice = originPrice;
    this.discountPrice = discountPrice;
    this.currency = currency;
    this.soldCount = soldCount;
    this.deleteFlag = deleteFlag;
  }
}
