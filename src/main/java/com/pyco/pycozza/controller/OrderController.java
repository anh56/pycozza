package com.pyco.pycozza.controller;


import com.pyco.pycozza.api.OrderApi;
import com.pyco.pycozza.api.model.*;
import com.pyco.pycozza.model.Order;
import com.pyco.pycozza.model.Product;
import com.pyco.pycozza.model.User;
import com.pyco.pycozza.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS})
public class OrderController implements OrderApi{
    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> addOrder(@Valid CreateOrderRequest createOrderRequest) {
        Order newOrder = modelMapper.map(createOrderRequest, Order.class);
//        Order newOrder = buildOrder(createOrderRequest);
        orderService.createOrder(newOrder);
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        result.setId(newOrder.get_id().toString());
        result.setStatus(HttpStatus.CREATED.value());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // order/{email} not working
    @Override
    public ResponseEntity<OrderListResponse> getOrderHistory(String email) {

        if(email != null){
            List<Order> orderList = orderService.getOrderListByEmail(email);
            return buildOrderHistory(orderList);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    // order/email?email=
    @Override
    public ResponseEntity<OrderListResponse> getOrderHistoryParam(@NotNull @Valid String email) {
        if (email.equals(""))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        else {
        List<Order> orderList = orderService.getOrderListByEmail(email);
        return buildOrderHistory(orderList);
        }
    }

    private ResponseEntity<OrderListResponse> buildOrderHistory(List<Order> orderList){
        OrderListResponse orderHistory = new OrderListResponse();
        if (orderList != null ){
            orderList.forEach(item ->
                    orderHistory.addOrdersItem(
                            modelMapper.map(
                                    item, OrderResponseModel.class)));
        }
        return new ResponseEntity<>(orderHistory,HttpStatus.OK);
    }



//        private ResponseEntity<OrderListResponse> buildOrderListResponse(List<OrderResponse> orderList){
//        OrderListResponse orderListResponse = new OrderListResponse();
//        orderListResponse.setOrders();
//    }

//    private ResponseEntity<OrderResponseModel> buildOrderResponse(){}
//    private ResponseEntity<ProductResponseModel> buidProductResponse(){}
//private ResponseEntity<ProductListResponse> buildProductListResponse(List<Product> productList){
//    ProductListResponse productListResponse = new ProductListResponse();
//    if (productList != null){
//        productList.forEach(item -> productListResponse.addProductsItem(modelMapper.map(item, ProductResponseModel.class)));
//    }
//    return new ResponseEntity<>(productListResponse, HttpStatus.OK);
//}


    //    private Order buildOrder(CreateOrderRequest createOrderRequest){
//        Order newOrder = new Order();
//
//        List<Product> productList = new ArrayList<Product>();
//        List<OrderProductResponseModel> productResponseModelList = createOrderRequest.getCart();
//
//        User user = new User();
//        UserResponseModel userInstance = createOrderRequest.getOrderUserInformation();
//
//        productResponseModelList.forEach(item -> productList.add(modelMapper.map(item, Product.class)));
//
//        newOrder.setCart(productList);
//
//        user.setFullName(userInstance.getFullName());
//        user.setAddress(userInstance.getAddress());
//        user.setPhone(userInstance.getPhone());
//
//        newOrder.setOrderUserInformation(user);
//
//        newOrder.setEmail(createOrderRequest.getEmail());
//        newOrder.setNote(createOrderRequest.getNote());
//        newOrder.setOrderTime(createOrderRequest.getOrderTime());
//        newOrder.setPaymentMethod(createOrderRequest.getPaymentMethod());
//        newOrder.setTotalPrice(createOrderRequest.getTotalPrice());
//
//
//        return newOrder;
//    }


}
