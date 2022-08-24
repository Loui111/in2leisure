package com.in2l.domain.member.exception;

import com.in2l.global.error.In2Exception;

public class MemberNotFound extends In2Exception {

  private static final String MESSAGE = "사용자를 찾을 수 없습니다.";

  public MemberNotFound() {
    super(MESSAGE);
  }

  @Override
  public int getStatusCode() {
    return 404;
  }
}
