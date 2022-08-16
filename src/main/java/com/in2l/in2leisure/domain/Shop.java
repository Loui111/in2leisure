package com.in2l.in2leisure.domain;

import com.in2l.in2leisure.domain.enums.Conturies;
import com.in2l.in2leisure.domain.enums.SubTypes;
import com.in2l.in2leisure.domain.enums.Types;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "shop")
public class Shop extends BaseDateTime{

  @Id
  @Column(name = "shop_id")
  private Long id;

  private Types types;
  private SubTypes subTypes;    //type, subtype의 정책 (ENUM vs DB) 고민해야함.
  private String desc;

  @Enumerated(EnumType.STRING)
  private Conturies country;

  private String frontImage;
  private String contentImage;
  private float reviewImage;
  private float rankScore;

}
