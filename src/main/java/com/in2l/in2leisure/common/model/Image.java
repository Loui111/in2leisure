package com.in2l.in2leisure.common.model;

import lombok.Getter;

@Getter
public class Image {

  //이미지 단위를 어떻게 할지는 고민이 필요
  //small: 25x25
  //mid: 150x150
  //big: 400x400 등 직사각, 정사각 등.
  private String smallImage;
  private String midImage;
  private String bigImage;

}
