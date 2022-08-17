package com.in2l.in2leisure.home.model;

import com.in2l.in2leisure.common.model.Footer;
import com.in2l.in2leisure.common.model.Header;
import com.in2l.in2leisure.common.model.Image;
import com.in2l.in2leisure.common.model.ImageSlide;
import java.util.ArrayList;
import lombok.Getter;

@Getter
public class homeResponseModel {    //홈화면을 구성하기 위한 모든 데이터.

  //Home header
  private Header homeHeader;

  //Home imageslide
  private ArrayList<ImageSlide> homeImageSlide = new ArrayList<>();

  //Home Type   //이건 바둑판식 모양.
  private ArrayList<Image> typeImage = new ArrayList<>();

  //Home real top10
  private ArrayList<ImageSlide> homeTopTen = new ArrayList<>();

  //Home Now DC
  private ArrayList<ImageSlide> homeNowDc = new ArrayList<>();

  //Home Now near
  private ArrayList<ImageSlide> homeNowNear = new ArrayList<>();

  //Home Footer
  private Footer homeFooter;


}
