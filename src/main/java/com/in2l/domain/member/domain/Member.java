package com.in2l.domain.member.domain;

import com.in2l.domain.member.exception.InvalidMemberRequest;
import com.in2l.global.common.domain.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
//@NoArgsConstructor(access = AccessLevel.PUBLIC)  //Entity는 기본 생성자를 넣어줘야 한다? 그게 먼 소리지?
@NoArgsConstructor
@Table(name = "member")
public class Member{

  /**
   * @email            :String
   * @password     :String
   * @memberName    :String
   * phoneNumber  :String? Int?
   * gender         :ENUM
   * birthDay        :DateTime
   * address       :String
   * profileImage :String
   */

  @Id
  @Column(name = "member_id")
  @GeneratedValue
//  @NotBlank
  private Long member_id;

  @NotBlank
  private String email;

  @NotBlank
  private String password;

  @NotBlank
  private String memberName;

  private String phoneNumber;

  private GenderType gender;

  private LocalDateTime birthDay;

  private String address;

  private String profileImage;

  @Builder
  public Member(Long member_id, @NotBlank String email,
      @NotBlank String password, @NotBlank String memberName, String phoneNumber,
      GenderType gender, LocalDateTime birthDay, String address, String profileImage) {
    this.member_id = member_id;
    this.email = email;
    this.password = password;
    this.memberName = memberName;
    this.phoneNumber = phoneNumber;
    this.gender = gender;
    this.birthDay = birthDay;
    this.address = address;
    this.profileImage = profileImage;
  }

  //validation은 MemberRequest 에서
//  public void validate(){
//    if( title.contains("바보")){
//      throw new InvalidRequest("title", "제목에 바보를 포함할 수 없습니다.");
//      //exception 안에 이런 정보를 포함시켜 놓고,
//    }
//  }
}

