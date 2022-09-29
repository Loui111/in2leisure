package com.in2l;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Log4j2
@SpringBootApplication
@EnableJpaAuditing()//BaseTimeEntity 때문에 씀.
//TODO: Spring의 config설정을 모아놓는 config클래스가 있긴 해야함
public class In2lApplication {

  //TODO: Logger역시 Config 설정을 따로 모아놓아야함.


  public static void main(String[] args) {
    SpringApplication.run(In2lApplication.class, args);
  }
}
