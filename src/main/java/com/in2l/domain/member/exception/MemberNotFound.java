package com.in2l.domain.member.exception;

import com.in2l.global.error.In2Exception;

public class MemberNotFound extends In2Exception {

  private static final String MESSAGE = "사용자를 찾을 수 없습니다.";    //TODO: 글로벌이 될까?

  public MemberNotFound() {
    super(MESSAGE);
  }

  public MemberNotFound(Throwable cause){
    super(MESSAGE, cause);
  }

  @Override
  public int getStatusCode() {
    return 404;
  }
}
