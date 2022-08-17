package com.in2l.in2leisure.domain.common;

import com.in2l.in2leisure.domain.BaseDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "types")      //RDB의 types 중복제거
public class Types extends BaseDateTime {

  @Id
  @Column(name = "type_id")
  private Long id;

  private Long parentId;     //Type인 경우는 필요 없음. Type: 0, subType: 1, deatil은 필요할까??
  private String typeName;
  private String typeImage;
}
