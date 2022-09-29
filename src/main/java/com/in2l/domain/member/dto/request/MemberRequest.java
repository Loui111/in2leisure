package com.in2l.domain.member.dto.request;

import com.in2l.domain.member.domain.GenderType;
import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.exception.InvalidMemberRequest;
import com.in2l.global.common.domain.BaseTimeEntity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@NoArgsConstructor    //이거 없으면 json오류 났었음.
@ToString
public class  MemberRequest{

  @NotBlank
  @Email(message = "이메일 형식이 올바르지 않습니다.")
  private String email;

  @NotBlank
  @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
      message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
  private String password;

  @NotBlank
//  @Max(50) @Min(1)      //걍 생각없이 50으로 잡음.
  //TODO: Validation fail하면 Exception 출력하게 해야함.
  private String name;

  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  private GenderType gender;

//  @DateTimeFormat(pattern = "yyyyMMdd")   //TODO: 이거 왜 안되냥.
  private String birthDay;

  private String address;

  private String profileImage;      //TODO: 확장자 제한 있어야함.

  private boolean deleteFlag;

  @Builder
  public MemberRequest(
      @NotBlank @Email(message = "이메일 형식이 올바르지 않습니다.") String email,
      @NotBlank @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
          message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.") String password,
      @NotBlank String name, String phoneNumber, GenderType gender, String birthDay,
      String address, String profileImage, boolean deleteFlag) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.gender = gender;
    this.birthDay = birthDay;
    this.address = address;
    this.profileImage = profileImage;
    this.deleteFlag = deleteFlag;
  }

  public void setDeleteFlag(boolean deleteFlag) {   //TODO: 이거 지워야함. 근데 어떻게??
    this.deleteFlag = deleteFlag;
  }

  public static MemberRequest of(Member member1) {

    return MemberRequest.builder()
        .email(member1.getEmail())
        .password(member1.getPassword())
        .name(member1.getName())
        .phoneNumber(member1.getPhoneNumber())
        .gender(member1.getGender())
        .birthDay(member1.getBirthDay().toString())
        .address(member1.getAddress())
        .profileImage(member1.getProfileImage())
        .deleteFlag(member1.isDeleteFlag())
        .build();
  }

  public void NameValidation() {
    if (name.contains("sucks")) {  //TODO: key:value로 contains를 체크해야할듯.
      throw new InvalidMemberRequest("대화명에 비속어는 포함할 수 없습니다!!");
    }
  }
}