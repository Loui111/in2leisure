package com.in2l.domain.orders.service;

import com.in2l.domain.orders.domain.Orders;
import com.in2l.domain.orders.domain.OrdersProduct;
import com.in2l.domain.orders.dto.request.OrdersRequest;
import com.in2l.domain.orders.dto.response.OrdersResponse;
import com.in2l.domain.orders.exception.OrderNotFound;
import com.in2l.domain.orders.repository.OrdersRepository;
import com.in2l.domain.product.domain.Product;
import com.in2l.domain.product.domain.Product.ProductBuilder;
import com.in2l.domain.product.dto.request.ProductRequestDto;
import com.in2l.global.common.domain.Currency;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrdersService {

  private final OrdersRepository ordersRepository;

  public Orders selectOrders(Long ordersId) {
    Orders orders = ordersRepository.findById(ordersId)
        .orElseThrow(()-> new OrderNotFound());

    return orders;
  }

  public OrdersResponse createOrders(OrdersRequest ordersRequest) {

    /**
     * member_id        :Long
     * shop_id        :Long
     * shopName     :String
     * originPrice    :Long
     * discountPrice: Long
     * discountRate : float
     * currency        :ENUM
     * orderStatus    : ENUM
     */

      //TODO: orders create 함수가 있어야 할듯.
    Orders orders = Orders.builder()
        .member_id(ordersRequest.getMember_id())
//        .user_id( getUserFromSession() )    //TODO: 세션에서 userID가져오기.
        .shop_id(ordersRequest.getShop_id())
        .shopName(ordersRequest.getShopName())
        .originPrice(ordersRequest.getOriginPrice())
        .discountPrice(ordersRequest.getDiscountPrice())
        .discountRate(ordersRequest.getDiscountRate())
        .currency(ordersRequest.getCurrency())
        .orderStatus(ordersRequest.getOrderStatus())
        .build();

    List<ProductRequestDto> productRequestDtoList = ordersRequest.getProductList();

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



//    private Long product_id;
//
//    private String productName;
//
//    private String productDesc;
//
//    private Long amount;
//
//    private Long originPrice;
//
//    private Long discountPrice;
//
//    @Enumerated(EnumType.STRING)
//    private Currency currency;


    for( ProductRequestDto productRequestDto : productRequestDtoList){
      Product product = Product.builder()
          .productDesc(productRequestDto.getProductDesc())
          .productName(productRequestDto.getProductName())
          .amount(productRequestDto.getAmount())
          .originPrice(productRequestDto.getOriginPrice())
          .discountPrice(productRequestDto.getDiscountPrice())
          .currency(productRequestDto.getCurrency())
          .build();

      OrdersProduct ordersProduct = OrdersProduct.builder()
          .orders(orders)
          .product(product)
          .build();
//
//      OrderItems orderItems = OrderItems.builder()
//          .orders(orders)
//          .product(product)
//          .build();
    }

    Orders savedOrders = ordersRepository.save(orders);   //cascade니까 orderItems도 들어가겠지?

    OrdersResponse ordersResponse = OrdersResponse.builder()
        .orders_id(savedOrders.getOrders_id())
        .shop_id(savedOrders.getShop_id())
        .orderStatus(savedOrders.getOrderStatus())
        .build();

//    OrdersResponse ordersResponse = OrdersResponse.builder()
//        .orders_id(savedOrders.getOrders_id())
//        .orderItemsList(savedOrders.getOrderItemsList())
//        .shop_id(savedOrders.getShop_id())
//        .orderStatus(savedOrders.getOrderStatus())
//        .build();

    return ordersResponse;
  }
}
