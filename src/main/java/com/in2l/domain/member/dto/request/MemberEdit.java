package com.in2l.domain.member.dto.request;

import com.in2l.domain.member.domain.GenderType;
import com.in2l.domain.member.domain.Member;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberEdit{

  @Id
  private Long id;

  @NotBlank(message = "유효한 이메일이 아닙니다. ")
  @Email
  private String email;

  @NotBlank
  @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
      message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
  private String password;

  @NotBlank
  private String name;

  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  private GenderType gender;      //TODO: 젠더 validation 도 필요함.

  private String birthDay;

  private String address;

  private String profileImage;

  private boolean deleteFlag;

  @Builder
  public MemberEdit(
      @NotBlank(message = "유효한 이메일이 아닙니다. ") @Email String email,
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

  public static MemberEdit of(MemberRequest memberRequest) {
    return MemberEdit.builder()
        .email(memberRequest.getEmail())
        .password(memberRequest.getPassword())
        .name(memberRequest.getName())
        .phoneNumber(memberRequest.getPhoneNumber())
        .gender(memberRequest.getGender())
        .birthDay(memberRequest.getBirthDay())
        .address(memberRequest.getAddress())
        .profileImage(memberRequest.getProfileImage())
        .deleteFlag(memberRequest.isDeleteFlag())
        .build();
  }

  public static MemberEdit of(Member member1) {
    return MemberEdit.builder()
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
}
