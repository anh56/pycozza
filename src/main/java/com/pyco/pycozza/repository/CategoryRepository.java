package com.pyco.pycozza.repository;

import com.pyco.pycozza.model.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Integer>{
    Category findCategoriesByNameContaining(String key);
    Category findById(int id);
}
