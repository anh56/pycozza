package com.pyco.pycozza.api;

import com.pyco.pycozza.api.model.CreateProductRequest;
import com.pyco.pycozza.controller.ProductController;
import com.pyco.pycozza.model.Product;
import com.pyco.pycozza.service.ProductService;
import org.aspectj.asm.IModelFilter;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest
@AutoConfigureDataMongo
public class ProductApiTest {


    @Autowired
    private ProductController productController;

    @Autowired
    private ProductService productService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ModelMapper modelMapper;

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
    public void getProductByCategoryReturnEnoughQuantityTest(){
        List<Product> productList =  productController.getProductByCategory(1);
        Assert.assertEquals(1, productList.size());
    }

    @Test
    public void addProductReturnSuccessfulTest(){
        CreateProductRequest productRequest = new CreateProductRequest();
        productRequest.setName("test prod 3");
        productRequest.setCategoryId(2);
        productRequest.setImgLink("test Imng Link");
        productRequest.setDescription("test desc" );
        productRequest.setPrice(99);
        productController.addProduct(productRequest);
        Assert.assertEquals(1, productService.getProductByCategory(2).size());

    }
}
