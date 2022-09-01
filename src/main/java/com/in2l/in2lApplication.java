package com.in2l;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing      //BaseTimeEntity 때문에 씀.
public class in2lApplication {

  public static void main(String[] args) {
    SpringApplication.run(com.in2l.in2lApplication.class, args);
  }
}
