package com.pyco.pycozza.repository;

import com.pyco.pycozza.model.Category;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Integer>{
    Category findCategoriesByNameContaining(String key);
    Category findById(int id);
}
