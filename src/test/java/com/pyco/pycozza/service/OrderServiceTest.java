package com.pyco.pycozza.service;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pyco.pycozza.api.model.OrderProductResponseModel;
import com.pyco.pycozza.model.Order;
import com.pyco.pycozza.repository.OrderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureDataMongo
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OrderRepository orderRepository;

    private static final String TEST_USER_EMAIL = "test@gmail.com";

    //     public Order createOrder(Order order){
    //        return orderRepository.save(order);
    //    }
    //
    //    public List<Order> getOrderListByEmail(String email){
    //        return (List<Order>) orderRepository.findOrdersByEmail(email);
    //    }

    @BeforeEach
    public void inti(){
        mongoTemplate.remove(Order.class).all();
        OrderProductResponseModel testOrder = new OrderProductResponseModel();
        testOrder.setName("Test product order 1");
        testOrder.setPrice(99);
        testOrder.setQuantity(3);
        List<OrderProductResponseModel> cart = new ArrayList<OrderProductResponseModel>();
        cart.add(testOrder);
        Order order = new Order();
        order.setCart(cart);
        order.setEmail(TEST_USER_EMAIL);
        mongoTemplate.save(order);
    }

    @Test
    public void findOrderByEmailTest(){
        List<Order> orderList = orderService.getOrderListByEmail(TEST_USER_EMAIL);
        Assert.assertEquals(1,orderList.size());
        Assert.assertEquals(orderList.get(0).getEmail(), TEST_USER_EMAIL);
    }


    @Test
    public void createOrderTest(){
        Order mockOrderWithNoData = new Order();
        orderService.createOrder(mockOrderWithNoData);
        Assert.assertEquals(2, orderRepository.findAll().size());
    }



}
