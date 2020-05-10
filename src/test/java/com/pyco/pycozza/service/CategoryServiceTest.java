package com.pyco.pycozza.service;


import com.pyco.pycozza.model.Category;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
@AutoConfigureDataMongo
public class CategoryServiceTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    public void init(){
        mongoTemplate.remove(Category.class).all();
        mongoTemplate.save(new Category(99, "test cate"));
    }

    @Test
    public void getListReturnEnoughCategoryTest(){
       Assert.assertEquals(1, categoryService.getList().size());
    }


}
