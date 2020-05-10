package com.pyco.pycozza.service;

import com.pyco.pycozza.model.Order;
import com.pyco.pycozza.model.QOrder;
import com.pyco.pycozza.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    public List<Order> getOrderListByEmail(String email){
        return orderRepository.findOrdersByEmail(email);
    }


}
