package com.in2l.domain.order.service;

import com.in2l.domain.order.domain.Order;
import com.in2l.domain.order.domain.OrderProduct;
import com.in2l.domain.order.dto.request.OrderRequest;
import com.in2l.domain.order.dto.response.OrderResponse;
import com.in2l.domain.order.exception.OrderNotFound;
import com.in2l.domain.order.repository.OrdersProductRepository;
import com.in2l.domain.order.repository.OrdersRepository;
import com.in2l.domain.product.domain.Product;
import com.in2l.domain.product.dto.request.ProductRequestDto;
import com.in2l.domain.product.dto.response.ProductResponseDto;
import com.in2l.domain.product.exception.ProductNotFound;
import com.in2l.domain.product.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

  private final OrdersRepository ordersRepository;

  private final OrdersProductRepository ordersProductRepository;

  private final ProductRepository productRepository;

  public OrderResponse getOrder(Long ordersId) {
    Order savedOrder = ordersRepository.findById(ordersId)
        .orElseThrow(() -> new OrderNotFound());

//    OrderResponse of = OrderResponse.of(savedOrder);

    return OrderResponse.of(savedOrder);
  }

  public OrderResponse postOrder(OrderRequest orderRequest) {

    //requestDTO에서 list를 받아옴.
    List<ProductRequestDto> productList = orderRequest.getProductList();

    //3. requestDTO를 통해 order 생성
    //TODO: 밑에 product.findbyId가 fail이믄 order자체가 캔슬되어야 하는데?
    Order savedOrder = createOrders(orderRequest);

    //product가 있는지 하나씩 돌아가면서 확인
    List<Long> productIds = productList.stream().map(p -> p.getProduct_id()).collect(Collectors.toList());
    List<Product> products = this.productRepository.findAllById(productIds);
    if(products.size() != productIds.size()){
      throw new ProductNotFound();
    }

    List<OrderProduct> orderProducts = products.stream().map(p -> OrderProduct.builder()
        .product(p)
        .order(savedOrder)
        .build()).collect(Collectors.toList());
    ordersProductRepository.saveAll(orderProducts);

    List<ProductResponseDto> productResponseDtos = productList.stream().map(p -> ProductResponseDto.builder()
        .product_id(p.getProduct_id())
        .productName(p.getProductName())
        .buyCount(p.getBuyCount())    //이건 Dto에서
        .build()).collect(Collectors.toList());

    //프론트에 던져줄 OrderResponse = 1order + N Product
    OrderResponse ordersResponse = OrderResponse.builder()
        .order_id(savedOrder.getOrder_id())
        .shop_id(savedOrder.getShop_id())
        .orderStatus(savedOrder.getOrderStatus())
//        .productResponseDtoList(productResponseDtos)    //TODO: 나중에 살리자.
        .build();

    return ordersResponse;
    }


//    Orders savedOrders = createOrders(ordersRequest);
//
//    List<ProductRequestDto> productRequestDtoList = ordersRequest.getProductList();
//
//    for (ProductRequestDto productRequestDto : productRequestDtoList) {
//      Product product1 = Product.builder()    //TODO: product create 함수 필요?
//          .shopName(ordersRequest.getShopName())
//          .productName(productRequestDto.getProductName())
//          .productDesc(productRequestDto.getProductDesc())
//          .amount(productRequestDto
//              .getBuyCount())   //TODO: 현재 amount불러와서 뺀다음에 다시 밀어넣는로직 필요. (-1면 exception도 필요)
//          .originPrice(productRequestDto.getOriginPrice())
//          .discountPrice(productRequestDto.getDiscountPrice())
//          .currency(productRequestDto.getCurrency())
//          .soldCount(productRequestDto.getBuyCount()) //TODO: 현재 팔린카운트에 ++ 하는 로직 필요.
//          .build();
//
//      Product savedProduct = productRepository.save(product1);
//
//      OrdersProduct ordersProduct = OrdersProduct.builder()
//          .product(savedProduct)
//          .order(savedOrders)
//          .build();
//
//      ordersProductRepository.save(ordersProduct);
//    }
//    OrdersResponse ordersResponse = OrdersResponse.builder()
//        .orders_id(savedOrders.getOrders_id())
//        .shop_id(savedOrders.getShop_id())
//        .orderStatus(savedOrders.getOrderStatus())
//        .productRequestDtoList(productRequestDtoList)
//        .build();
//
//    return ordersResponse

  private Order createOrders(OrderRequest orderRequest) {

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

    Order order = Order.builder()
        .member_id(orderRequest.getMember_id())
        .memberName(orderRequest.getMemberName())
        .shop_id(orderRequest.getShop_id())
        .shopName(orderRequest.getShopName())
        .originPrice(orderRequest.getOriginPrice())
        .discountPrice(orderRequest.getDiscountPrice())
        .discountRate(orderRequest.getDiscountRate())
        .currency(orderRequest.getCurrency())
        .orderStatus(orderRequest.getOrderStatus())
        .build();

    Order order1 = ordersRepository.save(order);

    return order1;
  }
}
