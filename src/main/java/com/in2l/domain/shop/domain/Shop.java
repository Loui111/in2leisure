package com.in2l.domain.shop.domain;

import com.in2l.global.common.domain.Country;
import com.in2l.global.common.domain.SubTypes;
import com.in2l.global.common.domain.Types;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "shop")
public class Shop {

  /**
   * type                : ENUM
   * subType         : ENUM
   * desc               : String
   * country           : ENUM
   * frontImage.     : String
   * contentImage : String
   * reviewScore    : float
   * rankScore       : float
   */

  @Id
  @GeneratedValue
  private Long shop_id;

  @Enumerated(EnumType.STRING)
  private Types types;

  @Enumerated(EnumType.STRING)
  private SubTypes subTypes;

  //TODO: SHOP을 불러올때마다 Product를 과연 빈번하게 호출하는게 맞는지 고민이 필요함.
//  private List<Product> productList = new ArrayList<>();

  private String desc;

  @Enumerated(EnumType.STRING)
  private Country country;

  private String frontImage;

  private String contentImage;

  private float reviewScore;

  private float rankScore;
}
