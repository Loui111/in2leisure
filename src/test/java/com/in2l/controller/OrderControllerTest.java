package com.in2l.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in2l.domain.order.domain.OrderStatus;
import com.in2l.domain.order.dto.request.OrderRequest;
import com.in2l.domain.order.repository.OrdersProductRepository;
import com.in2l.domain.order.repository.OrdersRepository;
import com.in2l.domain.order.service.OrderService;
import com.in2l.domain.product.domain.Product;
import com.in2l.domain.product.dto.request.ProductRequestDto;
import com.in2l.domain.product.repository.ProductRepository;
import com.in2l.global.common.domain.Currency;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
@Transactional
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


  @BeforeEach
//  @AfterEach
  void DBClean1() {   //TODO: 테스크코드를 하나씩 하면 성공함. 한번에 하면 실패.
    ordersProductRepository.deleteAllInBatch();
    productRepository.deleteAllInBatch();
    ordersRepository.deleteAllInBatch();

//    databaseCleanup.execute();

//    ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
    //entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
    //        }

    entityManager.createNativeQuery("ALTER TABLE order_product AUTO_INCREMENT 1").executeUpdate();
    entityManager.createNativeQuery("ALTER TABLE orders AUTO_INCREMENT 1").executeUpdate();
    entityManager.createNativeQuery("ALTER TABLE product AUTO_INCREMENT 1").executeUpdate();
  }

  Long testMember_id = 1L;
  String testName = "고냥인";
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
    mockMvc.perform(post("/api/v0/1/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json));

    //then
    mockMvc.perform(get("/api/v0/1/order/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Shop에서 2개의 제품 구매")
  void Orders2ProductsFrom1Shop() throws Exception {

    OrderRequest orderRequest = OrderGenerator();   //2개 제품 order request

    String json = objectMapper.writeValueAsString(orderRequest);

    mockMvc.perform(post("/api/v0/1/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
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
    mockMvc.perform(post("/api/v0/1/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json));

    //then
    mockMvc.perform(delete("/api/v0/1/order/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("order1개를 patch. (근데 어차피 post를 먼저 수행함)")
  @Transactional
  void patchOrder1() throws Exception{

    //Given
    OrderRequest orderRequest = OrderGenerator();   //2개 제품 order request

    String json = objectMapper.writeValueAsString(orderRequest);

    //when    //어차피 1post가 정상이어야 동작함.
    mockMvc.perform(post("/api/v0/1/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json));

    //then
    OrderRequest newOrderRequest = OrderRequest.builder()
        .memberId(testMember_id)
        .memberName(testName)
        .shopId(testShop_id)
        .shopName(testShopName)
        .originPrice(testOriginPrice)
        .discountPrice(30000L)
        .discountRate(testDiscountRate)
        .currency(testCurrency)
        .orderStatus(OrderStatus.PAID)    //갱신.
//        .products(productRequestDtos)
        .build();

    mockMvc.perform(patch("/api/v0/1/order/{id}", 1)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(newOrderRequest)))
        .andExpect(jsonPath("$.message").value("Order가 갱신되었습니다."))
        .andExpect(jsonPath("$.orderStatus").value("PAID"))   //OrderStatus.PAID 이게 외않되?
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("order1개의 deleteFlag를 update")
  @Transactional

  void updateDeleteFlagOrder() throws Exception{

    //Given
    OrderRequest orderRequest = OrderGenerator();   //2개 제품 order request

    String json = objectMapper.writeValueAsString(orderRequest);

    mockMvc.perform(post("/api/v0/1/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json));

    //when
    OrderRequest newOrderRequest = OrderRequest.builder()
        .memberId(testMember_id)
        .memberName(testName)
        .shopId(testShop_id)
        .shopName(testShopName)
        .originPrice(testOriginPrice)
        .discountPrice(testDiscountPrice)
        .discountRate(testDiscountRate)
        .currency(testCurrency)
        .orderStatus(OrderStatus.PAID)    //갱신.
        .deleteFlag(true)
//        .products(productRequestDtos)
        .build();

    //then
    mockMvc.perform(patch("/api/v0/1/order/{id}", 1)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(newOrderRequest)))
//        .andExpect(jsonPath("deleteFlag").value("true"))
        .andExpect(jsonPath("message").value("Order: 1 의 delete가 처리되었습니다."))
        .andExpect(jsonPath("id").value(1L))   //여기가 핵심. id가 변경되면 안된다..andExpect(status().isOk())
        .andDo(print());
  }


  private OrderRequest OrderGenerator() {
    ProductGenerator();

    ProductRequestDto productRequestDto1 = ProductRequestDto.builder()
        .productId(savedProduct1.getId())
        .productName(savedProduct1.getProductName())
        .productDesc(savedProduct1.getProductDesc())
        .buyCount(10L)
        .originPrice(100000L)
        .discountPrice(80000L)
        .build();

    ProductRequestDto productRequestDto2 = ProductRequestDto.builder()
        .productId(savedProduct2.getId())
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
        .memberId(testMember_id)
        .memberName(testName)
        .shopId(testShop_id)
        .shopName(testShopName)
        .originPrice(testOriginPrice)
        .discountPrice(testDiscountPrice)
        .discountRate(testDiscountRate)
        .currency(testCurrency)
        .orderStatus(testOrderStatus)
        .products(productRequestDtos)
        .build();
  }

  private void ProductGenerator() {
    Product product1 = Product.builder()
        .productDesc(testProductDesc1)
        .productName(testProductName1)
        .amount(100L)
        .discountPrice(10000L)
        .originPrice(120000L)
        .shopId(1L)
        .shopName("그냥샵")
        .soldCount(100L)
        .build();

    Product product2 = Product.builder()
        .productDesc(testProductDesc2)
        .productName(testProductName2)
        .amount(2000L)
        .discountPrice(10000L)
        .originPrice(120000L)
        .shopId(1L)
        .shopName("그냥샵")
        .soldCount(100L)
        .build();

    savedProduct1 = productRepository.save(product1);
    savedProduct2 = productRepository.save(product2);
  }

}