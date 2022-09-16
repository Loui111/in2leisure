package com.in2l.domain.order.exception;

import com.in2l.global.error.In2Exception;

public class OrderNotFound extends In2Exception {

  private static final String MESSAGE = "잘못된 주문정보입니다.";

  public OrderNotFound() {
    super(MESSAGE);
  }

  @Override
  public int getStatusCode() {
    return 404;
  }
}
