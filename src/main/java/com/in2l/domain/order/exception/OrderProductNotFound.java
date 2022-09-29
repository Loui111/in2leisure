package com.in2l.domain.order.exception;

import com.in2l.global.exceptions.In2Exception;

public class OrderProductNotFound extends In2Exception {

  private static final String MESSAGE = "잘못된 주문정보입니다.";

  public OrderProductNotFound() {
    super(MESSAGE);
  }

  @Override
  public int getStatusCode() {
    return 404;
  }
}
