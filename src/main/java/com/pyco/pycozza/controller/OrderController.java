package com.pyco.pycozza.controller;


import com.pyco.pycozza.api.OrderApi;
import com.pyco.pycozza.api.model.*;
import com.pyco.pycozza.model.Order;
import com.pyco.pycozza.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController implements OrderApi{
    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> addOrder(@Valid CreateOrderRequest createOrderRequest) {

        return null;
    }

    @Override
    public ResponseEntity<List<OrderListResponse>> getOrderHistory(String email) {
        OrderListResponse orderListResponse = new OrderListResponse();
        OrderResponseModel orderResponseModel = new OrderResponseModel();
        //implement set price, products, user
        return null;
    }

//    private ResponseEntity<OrderListResponse> buildOrderListResponse(List<OrderResponse> orderList){
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


//    @Override
//    public ResponseEntity<ObjectCreationSuccessResponse> addOrder(@Valid CreateOrderRequest createOrderRequest) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<List<OrderListResponse>> getOrder() {
//        return null;
//    }
//
}
