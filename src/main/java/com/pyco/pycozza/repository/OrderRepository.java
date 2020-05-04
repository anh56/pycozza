package com.pyco.pycozza.repository;

import com.pyco.pycozza.model.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, ObjectId>, QuerydslPredicateExecutor<Order> {
    List<Order> findOrdersByEmail(String email);
}
