package com.pyco.pycozza.service;


import com.pyco.pycozza.model.Product;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.List;

@SpringBootTest
@AutoConfigureDataMongo
public class ProductServiceTest {
    //    public Product(int id, String name, int categoryId, String imgLink, String description, int price, int maxPrice) {

    @Autowired
    private ProductService productService;

    @Autowired
    private MongoTemplate mongoTemplate;


    @BeforeEach
    public void init(){
        mongoTemplate.remove(Product.class).all();
        Product testPizza = new Product(1, "testPizza", 1,"testImgLink",
                "cheese","L","test desc", 999, 99999);
        mongoTemplate.save(testPizza);
        //int id, String name, int categoryId, String imgLink, int price
        Product testBeverage = new Product(3, "testBeverage", 3,
                "testImgLink", 99);
        mongoTemplate.save(testBeverage);
    }

    @Test
    public void testGetAllProductReturnEnoughQuantity(){
        List<Product> productList = productService.getAllProduct();
        Assert.assertEquals(2, productList.size());
    }

    @Test
    public void testGetProductCategoryIdReturnEnoughQuantity(){
        List<Product> productList = productService.getProductByCategory(1);
        Assert.assertEquals(1, productList.size());
    }

    @Test
    public void testGetProductPageReturnEnoughQuantity(){
        Page<Product> productList = productService.getProductList(0,2);
        Assert.assertEquals(2, productList.getSize());
    }

    @Test
    public void testInsertNewDuplicatedIdProduct(){
        productService.addNewProduct(1, "testPizza", 1,"testImgLink",
                "cheese","L",999,"test desc", 99999);
        List<Product> productList = productService.getAllProduct();
        Assert.assertEquals(2, productList.size());
    }











}
