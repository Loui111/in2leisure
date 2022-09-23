package com.in2l.domain.member.dto.response;

import com.in2l.domain.member.domain.GenderType;
import com.in2l.domain.member.domain.Member;
import java.time.LocalDateTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

  private Long id;
  private String email;
  private String name;
  private String phoneNumber;
  private GenderType gender;      //TODO: 젠더 validation 도 필요함.
  private LocalDateTime birthDay;
  private String address;
  private String profileImage;
  private Boolean deleteFlag;
  private String MESSAGE;

  @Builder
  public MemberResponse(Long id, String email, String name, String phoneNumber,
      GenderType gender, LocalDateTime birthDay, String address, String profileImage,
      Boolean deleteFlag, String MESSAGE) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.gender = gender;
    this.birthDay = birthDay;
    this.address = address;
    this.profileImage = profileImage;
    this.deleteFlag = deleteFlag;
    this.MESSAGE = MESSAGE;
  }

  public void setMESSAGE(String MESSAGE) {       //TODO: 이걸 여기다 set하는게 맞을까 과연??
    this.MESSAGE = MESSAGE;
  }

  public static MemberResponse of(Member member) {
    MemberResponse memberResponse = MemberResponse.builder()
        .id(member.getId())
        .email(member.getEmail())
        .name(member.getName())
        .phoneNumber(member.getPhoneNumber())
        .gender(member.getGender())
        .birthDay(member.getBirthDay())
        .address(member.getAddress())
        .profileImage(member.getProfileImage())
        .deleteFlag(member.getDeleteFlag())
        .build();

    return memberResponse;
  }
}
