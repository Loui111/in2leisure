package com.in2l.domain.orders.service;

import com.in2l.domain.orders.domain.Orders;
import com.in2l.domain.orders.dto.request.OrdersRequest;
import com.in2l.domain.orders.dto.response.OrdersResponse;
import com.in2l.domain.orders.exception.OrderNotFound;
import com.in2l.domain.orders.repository.OrdersRepository;
import com.in2l.domain.product.domain.Product;
import com.in2l.domain.product.domain.Product.ProductBuilder;
import com.in2l.domain.product.dto.request.ProductRequestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    for( ProductRequestDto productRequestDto : productRequestDtoList){
      Product product = Product.createProduct(productRequestDto, orders);
      orders.putProduct(product);
    }

    Orders savedOrders = ordersRepository.save(orders);

    OrdersResponse ordersResponse = OrdersResponse.builder()
        .orders_id(savedOrders.getOrders_id())
        .productList(savedOrders.getProductList())
        .shop_id(savedOrders.getShop_id())
        .orderStatus(savedOrders.getOrderStatus())
        .build();

    return ordersResponse;
  }
}
