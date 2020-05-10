package com.pyco.pycozza.repository;


import com.pyco.pycozza.api.model.OrderProductResponseModel;
import com.pyco.pycozza.model.Order;
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
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String TEST_USER_EMAIL = "test@gmail.com";

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
        List<Order> orders = orderRepository.findOrdersByEmail(TEST_USER_EMAIL);
        Assert.assertNotNull(orders);
        Assert.assertEquals(orders.get(0).getEmail(), TEST_USER_EMAIL);
    }



}
