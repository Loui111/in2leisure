package com.in2l.domain.member.dto.response;

import com.in2l.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

  private Long member_id;
  private String email;
  private String memberName;
  private String MESSAGE;

  @Builder
  public MemberResponse(Long member_id, String email, String memberName, String MESSAGE) {
    this.member_id = member_id;
    this.email = email;
    this.memberName = memberName;
    this.MESSAGE = MESSAGE;
  }

  public void setMESSAGE(String MESSAGE) {       //TODO: 이걸 여기다 set하는게 맞을까 과연??
    this.MESSAGE = MESSAGE;
  }

  public static MemberResponse of(Member member) {
    MemberResponse memberResponse = MemberResponse.builder()
        .member_id(member.getMember_id())
        .email(member.getEmail())
        .memberName(member.getMemberName())
        .build();

    return memberResponse;
  }
}
