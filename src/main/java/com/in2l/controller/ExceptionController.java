package com.in2l.controller;

import com.in2l.global.exceptions.ErrorResponse;
import com.in2l.global.exceptions.In2Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionController {

  @ResponseBody
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(In2Exception.class)
  public ResponseEntity<ErrorResponse> In2Exception(In2Exception e) {
    int code = e.getStatusCode();

    ErrorResponse body = ErrorResponse.builder()
        .code(String.valueOf(code))
        .validation(e.getValidation())
        .message(e.getMessage())
        .build();

    ResponseEntity<ErrorResponse> response = ResponseEntity.status(code)
        .body(body);

    return response;
  }
}
