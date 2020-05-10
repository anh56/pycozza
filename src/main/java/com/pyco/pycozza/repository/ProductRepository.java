package com.pyco.pycozza.repository;

import com.pyco.pycozza.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {
    List<Product> findByCategoryId(int categoryId);
    boolean existsById(int id);
}
