package com.in2l.in2leisure.home.controller;

import com.in2l.in2leisure.home.model.homeResponseModel;
import com.in2l.in2leisure.home.service.HomeService;
import com.in2l.in2leisure.user.model.userModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@ControllerAdvice
public class HomeController {
  private HomeService homeService;

  //header
  @GetMapping("/header")
  public homeResponseModel getHomeHeader(){
    //근데 헤더는 딱히 변하는게 아니라 front에서 static하게 관리하는게 맞을지도?

    return null;
  }

  //home Slide
  @GetMapping("/homeslide")
  public void getHomeSlide(){
    //슬라이드라면 이미지들의 list를 주면 될텐데.

  }

  //type
  @GetMapping("/hometype")
  public void getHomeType(){
    //TYpe은 ENUM일지 DB일지 아직은 미정.

    homeService.getTypes();


  }

  //top10
  @GetMapping("/hometop")
  public void getHomeTop(){

  }

  //now DC
  @GetMapping("/homedc")
  public void getHomeDc(){

  }

  @GetMapping("/homenear")
  public void getHomeNear(){

  }

  //
  @GetMapping("/homecompany")
  public void getHomeCompany(){

  }

  @GetMapping("/homefooter")
  public void getHomeFooter(){

  }

}
