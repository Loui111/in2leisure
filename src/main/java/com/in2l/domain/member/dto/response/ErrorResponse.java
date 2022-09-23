package com.in2l.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

/**
 * 뭔가 에러처리를 깔끔하게 하고 싶은데 뭔 방법없을까.
 */
@Getter
@JsonInclude(value = Include.NON_EMPTY)   //Json포맷을 줄때 empty인건 걍 뺴는거
public class ErrorResponse {

  private final String code;
  private final String message;
  private final Map<String, String> validation;   //근데 이건 final쓰면 오류 뜰수도 있음.
  //생성자에서 세팅해주니까 되긴 함. 허나 이후에 밀어 넣으려면 에러.

  @Builder
  public ErrorResponse(String code, String message, Map<String, String> validation) {
    this.code = code;
    this.message = message;
    this.validation = validation;
  }

  //addvalidation메소드를 하나 만듬.
  public void addValidation(String fieldName, String errorMessage) {
    this.validation.put(fieldName, errorMessage);
  }
}