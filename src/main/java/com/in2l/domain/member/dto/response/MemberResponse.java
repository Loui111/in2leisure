package com.in2l.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

  private Long member_id;
  private String email;
  private String memberName;
  private String message;

  @Builder
  public MemberResponse(Long member_id, String email, String memberName, String message) {
    this.member_id = member_id;
    this.email = email;
    this.memberName = memberName;
    this.message = message;
  }
}
