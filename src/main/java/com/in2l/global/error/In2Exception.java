package com.in2l.global.error;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public abstract class In2Exception extends RuntimeException{

  //에러 리턴을 json포맷 (키밸류) 로 보내기 위함.
  public final Map<String, String> validation = new HashMap<>();

  public In2Exception(String message) {
    super(message);
  }

  public In2Exception(String message, Throwable cause){
    super(message, cause);
  }

  public abstract int getStatusCode();   //지금은 아니고 상속받는 쪽에서 반드시 구현해라.

  //여기 validation 아마 프론트에 주는 메세지였을듯?
  public void addValidation(String fieldName, String message){
    validation.put(fieldName, message);
  }
}


