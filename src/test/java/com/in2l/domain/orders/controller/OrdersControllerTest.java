package com.in2l.domain.orders.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in2l.domain.member.domain.GenderType;
import com.in2l.domain.orders.domain.OrderStatus;
import com.in2l.domain.orders.domain.Orders;
import com.in2l.domain.orders.dto.request.OrdersRequest;
import com.in2l.domain.orders.repository.OrdersRepository;
import com.in2l.domain.orders.service.OrdersService;
import com.in2l.domain.product.domain.Product;
import com.in2l.global.common.domain.Currency;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class OrdersControllerTest {

  @Autowired
  private ObjectMapper objectMapper;    //json용

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private OrdersService ordersService;

  @Autowired
  private OrdersRepository ordersRepository;

  @BeforeEach
  void DBClean1() {
    ordersRepository.deleteAllInBatch();
  }

  @AfterEach
  void DBClean2() {
    ordersRepository.deleteAllInBatch();
  }

  /**
   * member_id        :Long
   * memberName       :String
   * shop_id        :Long
   * shopName     :String
   * originPrice    :Long
   * discountPrice: Long
   * discountRate : float
   * currency        :ENUM
   * orderStatus    : ENUM
   */

  Long testMember_id = 1L;
  String testMemberName = "고냥인";
  Long testShop_id = 1L;
  String testShopName = "in2ShopSports";
  Long testOriginPrice = 20000L;
  Long testDiscountPrice = 18000L;
  float testDiscountRate = 20f;     //TODO: 이게 float가 맞나?
  Currency testCurrency = Currency.KRW;
  OrderStatus testOrderStatus = OrderStatus.READY;

  @Test
  void getOrders() {
  }

  @Test
  void Test_createOrders() throws Exception{

    /**
     * shopName    : String
     * productName   :String
     * productDesc     :String
     * amount           :Long
     * originPrice      :Long
     * discountPrice  :Long
     * currency         :ENUM
     * soldCount        :Long
     */

    //Given
    Product product = Product.builder()
        .shop_id(1L)
        .shopName("shopnamesss")
        .productName("productname")
        .productDesc("descdescc")
        .amount(100L)
        .originPrice(10000L)
        .discountPrice(2000L)
        .currency(Currency.PHP)
        .soldCount(10L)
        .build();


    OrdersRequest ordersRequest = OrdersRequest.builder()
        .member_id(testMember_id)
        .memberName(testMemberName)
        .shop_id(testShop_id)
        .shopName(testShopName)
        .originPrice(testOriginPrice)
        .discountPrice(testDiscountPrice)
        .discountRate(testDiscountRate)
        .currency(testCurrency)
        .orderStatus(testOrderStatus)
        .build();

    String orderJson = objectMapper.writeValueAsString(ordersRequest);

//    Orders orders = Orders.builder()
//        .member_id(ordersRequest.getMember_id())
//        .memberName(ordersRequest.getMemberName())
//        .shop_id(ordersRequest.getShop_id())
//        .shopName(ordersRequest.getShopName())
//        .originPrice(ordersRequest.getOriginPrice())
//        .discountPrice(ordersRequest.getDiscountPrice())
//        .discountRate(ordersRequest.getDiscountRate())
//        .currency(ordersRequest.getCurrency())
//        .orderStatus(ordersRequest.getOrderStatus())
//        .build();

    mockMvc.perform(post("/v1/orders")
        .contentType(MediaType.APPLICATION_JSON)
        .content(orderJson))
        .andExpect(status().isOk())
        .andDo(print());
  }
}