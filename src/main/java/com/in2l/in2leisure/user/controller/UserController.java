package com.in2l.in2leisure.user.controller;

import com.in2l.in2leisure.user.model.userModel;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

//  @GetMapping("/user")      //user전체 검색은 admin 이 아니면 쓸수 없음.

  //
  @GetMapping("/user/{userId}")
  public userModel getUser(){

    return null;
  }

}
