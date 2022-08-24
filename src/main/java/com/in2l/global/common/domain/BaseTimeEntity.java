package com.in2l.global.common.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@Entity
@MappedSuperclass   //BaseEntity를 상속한 엔티티들은 BaseEntity에 있는 멤버변수들을 모두 컬럼으로 인식
public abstract class BaseTimeEntity {

  private String createBy;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;

}
