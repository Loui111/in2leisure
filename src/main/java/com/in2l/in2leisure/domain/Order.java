package com.in2l.in2leisure.domain;

import com.in2l.in2leisure.domain.enums.OrderStatus;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "orders")   //RDB의 order 중복제거
public class Order extends BaseDateTime{

  @Id
  @Column(name = "order_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

//  @OneToMany(mappedBy = "order")    //이게 맞나 이게?
//  private List<Option> optionList = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

//  private User user;
//  private ArrayList<Option> options;

//  private Shop shop_id;    //shop_id를 직접 연결 했다간 순환참조가 일어 날거 같은데...

//  private String OptionName;    //이것도 Option에 연관된건데?
//  private Long originPrice;
//  private Long discountPrice;
//  private Currencies currency;
//  private float discountRate;
//  private String shopName;
  //여깄는건 죄다 shop, user의 중복임.

}
