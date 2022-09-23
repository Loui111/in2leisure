package com.in2l.domain.member.domain;

import java.time.LocalDateTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberEditor {

  @NotBlank             //Email 변경을 가능하게 하는게 말이 될까?
  private String email;

  @NotBlank             //TODO: regetx valid 넣어야함 (memberEditor에 있음)
  private String password;

  @NotBlank
  private String name;

  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  private GenderType gender;      //TODO: 젠더 validation 도 필요함.

  private LocalDateTime birthDay;

  private String address;

  private String profileImage;

  private Boolean deleteFlag;

  @Builder    //.build() 가 없음.
  public MemberEditor(@NotBlank String email,
      @NotBlank String password, @NotBlank String name, String phoneNumber,
      GenderType gender, LocalDateTime birthDay, String address, String profileImage,
      Boolean deleteFlag) {
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

  public static MemberEditor of(MemberEditorBuilder memberEditorBuilder, Member originMember) {
    return memberEditorBuilder.email(originMember.getEmail())
        .password(originMember.getPassword())
        .name(originMember.getName())
        .phoneNumber(originMember.getPhoneNumber())
        .gender(originMember.getGender())
        .birthDay(originMember.getBirthDay())
        .address(originMember.getAddress())
        .profileImage(originMember.getProfileImage())
        .deleteFlag(originMember.getDeleteFlag())
        .build();
  }
}
