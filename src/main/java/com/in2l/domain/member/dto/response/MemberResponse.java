package com.in2l.domain.member.dto.response;

import com.in2l.domain.member.domain.GenderType;
import com.in2l.domain.member.domain.Member;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberResponse {

  private Long id;
  private String email;
  private String name;
  private String phoneNumber;
  private GenderType gender;      //TODO: 젠더 validation 도 필요함.
  private LocalDateTime birthDay;
  private String address;
  private String profileImage;
  private boolean deleteFlag;
  private String message;

  @Builder
  public MemberResponse(Long id, String email, String name, String phoneNumber,
      GenderType gender, LocalDateTime birthDay, String address, String profileImage,
      boolean deleteFlag, String message) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.gender = gender;
    this.birthDay = birthDay;
    this.address = address;
    this.profileImage = profileImage;
    this.deleteFlag = deleteFlag;
    this.message = message;
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
        .deleteFlag(member.isDeleteFlag())
        .build();

    return memberResponse;
  }
}
