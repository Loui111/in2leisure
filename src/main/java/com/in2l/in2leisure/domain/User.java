package com.in2l.in2leisure.domain;

import com.in2l.in2leisure.domain.enums.GenderTypes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "users")    //RDB의 user중복제거
public class User extends BaseDateTime{

  @Id
  @Column(name = "user_id")
  private Long id;

//  private List<Order> order = new ArrayList();    //user는 굳이 order를 알 필요는 없다?
                                              //양방향 연관관계는 최소화 하는것이 좋음. (현재 order -> user 단방향)
                                              //order에서 출발하면 됨. order를 통해 user를 알아내면 됨.
                                              //근데 왠지 내 비지니스에선 이게 필요할거 같은데??

  private Long email;
  private Long password;

//  @Column(length = 100)   //이런것도 넣을수 있음.
  private Long userName;
  private Long phoneNumber;

  @Enumerated(EnumType.STRING)
  private GenderTypes gender;

  private LocalDateTime birthDay;
  private String address;
  private String profileImage;      //Image는 Model로 갈까? big, medium, small 등으로.
//  private ImageModel profileImage;
//  private LocalDateTime registerDate;      //이건 그...데이터 그걸로 바꿔야 하는데 그....
//  private LocalDateTime updateDate;

}
