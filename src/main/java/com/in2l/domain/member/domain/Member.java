package com.in2l.domain.member.domain;

import com.in2l.global.common.domain.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
//@NoArgsConstructor(access = AccessLevel.PUBLIC)  //Entity는 기본 생성자를 넣어줘야 한다?
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseTimeEntity {

  /**
   * email            :String
   * password     :String
   * memberName    :String
   * phoneNumber  :String? Int?
   * gender         :ENUM
   * birthDay        :DateTime
   * address       :String
   * profileImage :String
   */

  @Id
  @Column(name = "member_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  public Member(@NotBlank String email,
      @NotBlank String password, @NotBlank String memberName, String phoneNumber,
      GenderType gender, LocalDateTime birthDay, String address, String profileImage) {
    this.email = email;
    this.password = password;
    this.memberName = memberName;
    this.phoneNumber = phoneNumber;
    this.gender = gender;
    this.birthDay = birthDay;
    this.address = address;
    this.profileImage = profileImage;
  }

  public MemberEditor.MemberEditorBuilder edit(){
    //setter대신 씀
    // public edit(String email, String password....){} 뭐 이런게 일반적이지만,
    // MemberEditor라는 도메인에 builder를 새로 선언해서
    // 여기다 변경될 코드를 선언해서 씀.

    MemberEditor.MemberEditorBuilder builder = MemberEditor.builder()
        .email(email)
        .password(password)
        .memberName(memberName)
        .phoneNumber(phoneNumber)
        .gender(gender)
        .birthDay(birthDay)
        .address(address)
        .profileImage(profileImage);

    return builder; //빌더 자체를 보냄.
  }

  //validation은 MemberRequest 에서
//  public void validate(){
//    if( title.contains("바보")){
//      throw new InvalidRequest("title", "제목에 바보를 포함할 수 없습니다.");
//      //exception 안에 이런 정보를 포함시켜 놓고,
//    }
//  }
}


