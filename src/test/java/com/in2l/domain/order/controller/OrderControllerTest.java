package com.in2l.domain.order.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in2l.domain.order.domain.OrderStatus;
import com.in2l.domain.order.dto.request.OrderRequest;
import com.in2l.domain.order.repository.OrdersRepository;
import com.in2l.domain.order.service.OrderService;
import com.in2l.domain.product.domain.Product;
import com.in2l.domain.product.dto.request.ProductRequestDto;
import com.in2l.domain.product.exception.ProductNotFound;
import com.in2l.domain.product.repository.ProductRepository;
import com.in2l.global.common.domain.Currency;
import java.util.ArrayList;
import java.util.List;
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
class OrderControllerTest {

  @Autowired
  private ObjectMapper objectMapper;    //json용

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private OrderService orderService;

  @Autowired
  private OrdersRepository ordersRepository;

  @Autowired
  private ProductRepository productRepository;

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

//  @Test
//  void getOrders() {
//  }
//
//  @Test
//  void Test_createOrders() throws Exception{
//
//    /**
//     * shopName    : String
//     * productName   :String
//     * productDesc     :String
//     * amount           :Long
//     * originPrice      :Long
//     * discountPrice  :Long
//     * currency         :ENUM
//     * soldCount        :Long
//     */
//
//    //Given
//
//    //이미 init으로 만들어서 필요 없음.
////    Product product = Product.builder()
////        .shop_id(1L)
////        .shopName("shopnamesss")
////        .productName("productname")
////        .productDesc("descdescc")
////        .amount(100L)
////        .originPrice(10000L)
////        .discountPrice(2000L)
////        .currency(Currency.KRW)
////        .soldCount(10L)
////        .build();
//
//    Product product1 = productRepository.findById(1L)
//        .orElseThrow( ()-> new ProductNotFound());
//    Product product2 = productRepository.findById(2L)
//        .orElseThrow( ()-> new ProductNotFound());
//    Product product3 = productRepository.findById(3L)
//        .orElseThrow( ()-> new ProductNotFound());
//
//    ProductRequestDto productRequestDto1 = ProductRequestDto.builder()
//        .product_id(product1.getProduct_id())
//        .productName(product1.getProductName())
//        .productDesc(product1.getProductDesc())
//        .buyCount(5L)    //5개 구
//        .originPrice(product1.getOriginPrice())
//        .discountPrice(product1.getDiscountPrice())
//        .currency(product1.getCurrency())
//        .build();
//
//    ProductRequestDto productRequestDto2 = ProductRequestDto.builder()
//        .product_id(product2.getProduct_id())
//        .productName(product2.getProductName())
//        .productDesc(product2.getProductDesc())
//        .buyCount(1L)
//        .originPrice(product2.getOriginPrice())
//        .discountPrice(product2.getDiscountPrice())
//        .currency(product2.getCurrency())
//        .build();
//
//    List<ProductRequestDto> productRequestDtoList = new ArrayList<>();
//    productRequestDtoList.add(productRequestDto1);
//    productRequestDtoList.add(productRequestDto2);
//
//    OrderRequest orderRequest = OrderRequest.builder()
//        .productList(productRequestDtoList)
//        .member_id(testMember_id)
//        .memberName(testMemberName)
//        .shop_id(testShop_id)   //shop_id는 product에서 가져와야하나?
//        .shopName(testShopName)
//        .originPrice(testOriginPrice)
//        .discountPrice(testDiscountPrice)
//        .discountRate(testDiscountRate)
//        .currency(testCurrency)
//        .orderStatus(testOrderStatus)
//        .build();
//
//    String orderJson = objectMapper.writeValueAsString(orderRequest);
//
////    Orders orders = Orders.builder()
////        .member_id(ordersRequest.getMember_id())
////        .memberName(ordersRequest.getMemberName())
////        .shop_id(ordersRequest.getShop_id())
////        .shopName(ordersRequest.getShopName())
////        .originPrice(ordersRequest.getOriginPrice())
////        .discountPrice(ordersRequest.getDiscountPrice())
////        .discountRate(ordersRequest.getDiscountRate())
////        .currency(ordersRequest.getCurrency())
////        .orderStatus(ordersRequest.getOrderStatus())
////        .build();
//
//    mockMvc.perform(post("/v1/orders")
//        .contentType(MediaType.APPLICATION_JSON)
//        .content(orderJson))
//        .andExpect(status().isOk())
//        .andDo(print());
//  }
}