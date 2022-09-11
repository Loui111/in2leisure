package com.in2l.domain.shop.domain;

import com.in2l.global.common.domain.BaseTimeEntity;
import com.in2l.global.common.domain.Country;
import com.in2l.global.common.domain.SubTypes;
import com.in2l.global.common.domain.Types;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "shop")
public class Shop extends BaseTimeEntity {

  /**
   * type                : ENUM
   * subType         : ENUM
   * shopDesc               : String
   * country           : ENUM
   * frontImage.     : String
   * contentImage : String
   * reviewScore    : float
   * rankScore       : float
   */

  @Id
  @Column(name = "shop_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long shop_id;

  @Enumerated(EnumType.STRING)
  private Types types;

  @Enumerated(EnumType.STRING)
  private SubTypes subTypes;

  private String shopDesc;

  @Enumerated(EnumType.STRING)
  private Country country;

  private String frontImage;

  private String contentImage;

  private float reviewScore;

  private float rankScore;

  @Builder
  public Shop(Types types, SubTypes subTypes, String shopDesc,
      Country country, String frontImage, String contentImage, float reviewScore, float rankScore) {
    this.types = types;
    this.subTypes = subTypes;
    this.shopDesc = shopDesc;
    this.country = country;
    this.frontImage = frontImage;
    this.contentImage = contentImage;
    this.reviewScore = reviewScore;
    this.rankScore = rankScore;
  }
}