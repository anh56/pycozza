package com.pyco.pycozza.controller;


import com.pyco.pycozza.model.Category;
import com.pyco.pycozza.model.Product;
import com.pyco.pycozza.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/list")
    public Page<Product> getProductList(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "5") int size){
        return productService.getProductList(page, size);
    }

    @GetMapping("/")
    public List<Product> getProductByCategory(@RequestParam int categoryId){
        return productService.getProductByCategory(categoryId);
    }

    @PostMapping("/add")
    public void addNewProduct(@RequestParam int id,
                                       @RequestParam String name,
                                       @RequestParam int categoryId,
                                       @RequestParam String imgLink,
                                       @RequestParam (required = false) String crust,
                                       @RequestParam (required = false) String size,
                                       @RequestParam (required = false) String description,
                                       @RequestParam int price
                                       ){
        productService.addNewProduct(id, name, categoryId, imgLink, crust, size ,price,description);
    }


}
