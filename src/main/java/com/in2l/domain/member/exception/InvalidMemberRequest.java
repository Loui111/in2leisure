package com.in2l.domain.member.exception;

import com.in2l.global.exceptions.In2Exception;

public class InvalidMemberRequest extends In2Exception {

  private static final String MESSAGE = "잘못된 요청입니다.";

  private String fieldName;
  private String message;

  public InvalidMemberRequest() {
    super(MESSAGE);
  }

  public InvalidMemberRequest(String message) {
    super(message);
  }

  public InvalidMemberRequest(String message, Throwable cause) {
    super(message, cause);
  }


  @Override
  public int getStatusCode() {
    return 400;   //404가 맞으려나??
  }
}
