package com.pyco.pycozza.controller;


import com.pyco.pycozza.api.OrderApi;
import com.pyco.pycozza.api.model.CreateOrderRequest;
import com.pyco.pycozza.api.model.ObjectCreationSuccessResponse;
import com.pyco.pycozza.api.model.OrderListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class OrderController implements OrderApi {
    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> addOrder(@Valid CreateOrderRequest createOrderRequest) {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderListResponse>> getOrder() {
        return null;
    }
}
