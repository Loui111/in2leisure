package com.in2l.in2leisure.domain;

import com.in2l.in2leisure.domain.enums.Currencies;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "options")      //RDB의 option 중복제거
public class Option extends BaseDateTime{

  @Id
  @Column(name = "option_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order orders;

  @ManyToOne
  @JoinColumn(name = "shop_id")
  private Shop shop;

//  private Order order;
//  private Shop shop;

  private String shopName;
  private String optionName;
  private String optionDesc;
  private Long amount;
  private Long originPrice;
  private Long discountPrice;

  @Enumerated(EnumType.STRING)
  private Currencies currency;

  private Long soldCount;
}
