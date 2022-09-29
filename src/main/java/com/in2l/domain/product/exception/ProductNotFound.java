package com.in2l.domain.product.exception;

import com.in2l.global.exceptions.In2Exception;

public class ProductNotFound extends In2Exception {

  private static final String MESSAGE = "Product을 찾을 수 없습니다.";

  public ProductNotFound() {
    super(MESSAGE);
  }

  @Override
  public int getStatusCode() {
    return 404;
  }

}
