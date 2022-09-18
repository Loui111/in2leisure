package com.in2l.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.RequestEntity.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.in2l.domain.order.domain.Order;
import com.in2l.domain.order.domain.OrderStatus;
import com.in2l.domain.order.dto.request.OrderRequest;
import com.in2l.domain.order.repository.OrdersProductRepository;
import com.in2l.domain.order.repository.OrdersRepository;
import com.in2l.domain.order.service.OrderService;
import com.in2l.domain.product.domain.Product;
import com.in2l.domain.product.dto.request.ProductRequestDto;
import com.in2l.domain.product.repository.ProductRepository;
import com.in2l.global.common.domain.Currency;
import com.in2l.global.util.DatabaseCleanup;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
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

  @Autowired
  private OrdersProductRepository ordersProductRepository;

  @PersistenceContext
  private EntityManager entityManager;


//  @Autowired
//  private DatabaseCleanup databaseCleanup;

  @BeforeEach
  void DBClean1() {   //TODO: 테스크코드를 하나씩 하면 성공함. 한번에 하면 실패.
    ordersProductRepository.deleteAllInBatch();
    productRepository.deleteAllInBatch();
    ordersRepository.deleteAllInBatch();

//    databaseCleanup.execute();

//    entityManager.createNativeQuery("TRUNCATE TABLE order_product").executeUpdate();
//    entityManager.createNativeQuery("TRUNCATE TABLE orders").executeUpdate();
//    entityManager.createNativeQuery("TRUNCATE TABLE product").executeUpdate();
  }

  Long testMember_id = 1L;
  String testMemberName = "고냥인";
  Long testShop_id = 1L;
  String testShopName = "in2ShopSports";
  Long testOriginPrice = 20000L;
  Long testDiscountPrice = 18000L;
  float testDiscountRate = 20f;     //TODO: 이게 float가 맞나?
  Currency testCurrency = Currency.KRW;
  OrderStatus testOrderStatus = OrderStatus.READY;

  String testProductName1 = "제품명 상세설명입니다.";
  String testProductName2 = "제품명 상세설명입니다222";
  String testProductDesc1 = "제품명11111";
  String testProductDesc2 = "제품명2222";

  Product savedProduct1;
  Product savedProduct2;

  @Test
  @DisplayName("order1개를 get. (근데 어차피 post를 먼저 수행함)")
  void getOrder1() throws Exception{

    //Given
    OrderRequest orderRequest = OrderGenerator();   //2개 제품 order request

    String json = objectMapper.writeValueAsString(orderRequest);

    //when    //어차피 1post가 정상이어야 동작함.
    mockMvc.perform(post("/v0/1/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json));

    //then
    mockMvc.perform(get("/v0/1/order/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Shop에서 2개의제품 구매")
  void Orders2ProductsFrom1Shop() throws Exception {

    OrderRequest orderRequest = OrderGenerator();   //2개 제품 order request

    String json = objectMapper.writeValueAsString(orderRequest);

    mockMvc.perform(post("/v0/1/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.order_id").value(1L))
        .andExpect(jsonPath("$.productResponseDtos.[0].productName").value(testProductName1))
        .andExpect(jsonPath("$.productResponseDtos.[1].productName").value(testProductName2))
        .andDo(print());
  }

  @Test
  @DisplayName("1개의 order를 삭제.")
  void deleteOrder() throws Exception {
    //Given
    OrderRequest orderRequest = OrderGenerator();   //2개 제품 order request

    String json = objectMapper.writeValueAsString(orderRequest);

    //when    //어차피 1post가 정상이어야 동작함.
    mockMvc.perform(post("/v0/1/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json));

    //then
    mockMvc.perform(get("/v0/1/order/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("order1개를 patch. (근데 어차피 post를 먼저 수행함)")
  void patchOrder1() throws Exception{

    //Given
    OrderRequest orderRequest = OrderGenerator();   //2개 제품 order request

    String json = objectMapper.writeValueAsString(orderRequest);

    //when    //어차피 1post가 정상이어야 동작함.
    mockMvc.perform(post("/v0/1/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json));

    //then

//    mockMvc.perform(patch("/v1/member/{memberId}", member1.getMember_id())
//      .contentType(MediaType.APPLICATION_JSON)
//        .content(objectMapper.writeValueAsString(memberRequest)))
//        .andExpect(status().isOk());
  }


//
//    mockMvc.perform(patch("/v0/1/order/{member_id}", orderRequest.getMember_id())
//      .contentType(MediaType.APPLICATION_JSON)
//        .content(json))
//        .andExpect(status().isOk());

//
//    mockMvc.perform(patch("/v0/1/order/1")
//        .contentType(MediaType.APPLICATION_JSON)
//        .content(json))
//        .andExpect(status().isOk());


  private OrderRequest OrderGenerator() {
    ProductGenerator();

    ProductRequestDto productRequestDto1 = ProductRequestDto.builder()
        .product_id(savedProduct1.getProduct_id())
        .productName(savedProduct1.getProductName())
        .productDesc(savedProduct1.getProductDesc())
        .buyCount(10L)
        .originPrice(100000L)
        .discountPrice(80000L)
        .build();

    ProductRequestDto productRequestDto2 = ProductRequestDto.builder()
        .product_id(savedProduct2.getProduct_id())
        .productName(savedProduct2.getProductName())
        .productDesc(savedProduct2.getProductDesc())
        .buyCount(5L)
        .originPrice(20000L)
        .discountPrice(1200L)
        .build();

    ArrayList<ProductRequestDto> productRequestDtos = new ArrayList<>();
    productRequestDtos.add(productRequestDto1);
    productRequestDtos.add(productRequestDto2);

    return OrderRequest.builder()
        .member_id(testMember_id)
        .memberName(testMemberName)
        .shop_id(testShop_id)
        .shopName(testShopName)
        .originPrice(testOriginPrice)
        .discountPrice(testDiscountPrice)
        .discountRate(testDiscountRate)
        .currency(testCurrency)
        .orderStatus(testOrderStatus)
        .productList(productRequestDtos)
        .build();
  }

  private void ProductGenerator() {
    Product product1 = Product.builder()
        .productDesc(testProductDesc1)
        .productName(testProductName1)
        .amount(100L)
        .discountPrice(10000L)
        .originPrice(120000L)
        .shop_id(1L)
        .shopName("그냥샵")
        .soldCount(100L)
        .build();

    Product product2 = Product.builder()
        .productDesc(testProductDesc2)
        .productName(testProductName2)
        .amount(2000L)
        .discountPrice(10000L)
        .originPrice(120000L)
        .shop_id(1L)
        .shopName("그냥샵")
        .soldCount(100L)
        .build();

    savedProduct1 = productRepository.save(product1);
    savedProduct2 = productRepository.save(product2);
  }

}