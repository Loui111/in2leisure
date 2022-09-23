package com.in2l.domain.order.service;

import com.in2l.domain.member.exception.MemberNotFound;
import com.in2l.domain.order.domain.Order;
import com.in2l.domain.order.domain.OrderProduct;
import com.in2l.domain.order.dto.request.OrderRequest;
import com.in2l.domain.order.dto.response.OrderResponse;
import com.in2l.domain.order.exception.OrderNotFound;
import com.in2l.domain.order.exception.OrderProductNotFound;
import com.in2l.domain.order.repository.OrdersProductRepository;
import com.in2l.domain.order.repository.OrdersRepository;
import com.in2l.domain.product.domain.Product;
import com.in2l.domain.product.dto.request.ProductRequestDto;
import com.in2l.domain.product.dto.response.ProductResponseDto;
import com.in2l.domain.product.exception.ProductNotFound;
import com.in2l.domain.product.repository.ProductRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

    //TODO: delete flag 인건 빼고 get 해와야함.

    return OrderResponse.of(savedOrder).setMESSAGE("주문확인 완료.");
  }

  public OrderResponse postOrder(OrderRequest orderRequest) {

    //requestDTO에서 list를 받아옴.
    List<ProductRequestDto> productList = orderRequest.getProducts();

    //3. requestDTO를 통해 order 생성
    Order savedOrder = ordersRepository.save(Order.of(orderRequest));

    List<Long> productIds = productList.stream().map(p -> p.getProductId())
        .collect(Collectors.toList());
    List<Product> products = this.productRepository.findAllById(productIds);
    if (products.size() != productIds.size()) {
      throw new ProductNotFound();
    }//개수가 맞지 않으면 == 없는 제품이 있다는거.

    List<OrderProduct> orderProducts = products.stream().map(p -> OrderProduct.builder()
        .product(p)
        .order(savedOrder)
        .build()).collect(Collectors.toList());
    ordersProductRepository.saveAll(orderProducts);

    List<ProductResponseDto> productResponseDtos = productList.stream()
        .map(p -> ProductResponseDto.builder()
            .productId(p.getProductId())
            .productName(p.getProductName())
            .buyCount(p.getBuyCount())
            .build()).collect(Collectors.toList());

    //프론트에 던져줄 OrderResponse = 1order + N Product
    OrderResponse ordersResponse = OrderResponse.builder()
        .id(savedOrder.getId())
        .shopId(savedOrder.getShopId())
        .orderStatus(savedOrder.getOrderStatus())
        .productResponseDtos(productResponseDtos)
        .MESSAGE("주문이 완료되었습니다.")
        .build();

    return ordersResponse;
  }

  public OrderResponse deleteOrder(Long ordersId) {
    Order savedOrder = ordersRepository.findById(ordersId)
        .orElseThrow(() -> new OrderNotFound());

    return null;
  }
}
//
//    //1. order_product delete flag
//    //2. order에 delete flag
//
//    Long order_id = savedOrder.getOrder_id();
//
////    ordersProductRepository.findById(order_id);
////
////    Collection<OrderProduct> orderIds = ordersProductRepository.findByOrder_id(order_id); //이거 맞음??
////
////    System.out.println(orderIds);
//
//
////        .orElseThrow(() -> new OrderProductNotFound());
//
////    System.out.println(products);
//
////    List<OrderProduct> orderProducts = products.stream().map(p -> OrderProduct.builder()
////        .product(p)
////        .order(savedOrder)
////        .build()).collect(Collectors.toList());
//
////
////    List<OrderProduct> product_ids = ordersProductRepository.findById(ordersId)
////        .orElseThrow(() -> new OrderNotFound());
//
////    ordersRepository.delete(savedOrder);
//
//    return null;
//
////    return OrderResponse.of(savedOrder).setMESSAGE("주문이 삭제되었습니다.");
//  }



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

//  private Order createOrder(OrderRequest orderRequest) {
//
//    Order order = Order.builder()
//        .member_id(orderRequest.getId())
//        .name(orderRequest.getname())
//        .shop_id(orderRequest.getShop_id())
//        .shopName(orderRequest.getShopName())
//        .originPrice(orderRequest.getOriginPrice())
//        .discountPrice(orderRequest.getDiscountPrice())
//        .discountRate(orderRequest.getDiscountRate())
//        .currency(orderRequest.getCurrency())
//        .orderStatus(orderRequest.getOrderStatus())
//        .build();
//
//    Order order1 = ordersRepository.save(order);
//
//    return order1;
