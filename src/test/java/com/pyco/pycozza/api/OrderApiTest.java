package com.pyco.pycozza.api;


import com.pyco.pycozza.controller.OrderController;
import com.pyco.pycozza.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
@AutoConfigureDataMongo
public class OrderApiTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OrderController orderController;

    @Autowired
    private OrderService orderService;

    @Test
    private void addOrderSuccessfulTest(){}

    @Test
    private void getOrderHistorySuccessfulTest(){}

}
