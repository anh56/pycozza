package com.pyco.pycozza.controller;


import com.pyco.pycozza.api.ProductApi;
import com.pyco.pycozza.api.model.CreateProductRequest;
import com.pyco.pycozza.api.model.ObjectCreationSuccessResponse;
import com.pyco.pycozza.api.model.ProductListResponse;
import com.pyco.pycozza.api.model.ProductResponseModel;
import com.pyco.pycozza.model.Category;
import com.pyco.pycozza.model.Product;
import com.pyco.pycozza.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS})
public class ProductController implements ProductApi{

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/v1/list")
    public Page<Product> getProductList(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "5") int size){
        return productService.getProductList(page, size);
    }

    @GetMapping("/v1/")
    public List<Product> getProductByCategory(@RequestParam int categoryId){
        return productService.getProductByCategory(categoryId);
    }

    @PostMapping("/v1/add")
    public void addNewProduct(@RequestParam int id,
                                       @RequestParam String name,
                                       @RequestParam int categoryId,
                                       @RequestParam String imgLink,
                                       @RequestParam (required = false) String crust,
                                       @RequestParam (required = false) String size,
                                       @RequestParam (required = false) String description,
                                       @RequestParam int price,
                              @RequestParam int maxPrice
    ){
        productService.addNewProduct(id, name, categoryId, imgLink, crust, size ,price,description, maxPrice);
    }

    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> addProduct(@Valid CreateProductRequest createProductRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ProductListResponse> getProductByCategoryId(Integer categoryId) {
        List<Product> productListByCategory = productService.getProductByCategory(categoryId);
        return buildProductListResponse(productListByCategory);
    }

    private ResponseEntity<ProductListResponse> buildProductListResponse(List<Product> productList){
        ProductListResponse productListResponse = new ProductListResponse();
        if (productList != null){
            productList.forEach(item -> productListResponse.addProductsItem(modelMapper.map(item, ProductResponseModel.class)));
        }
        return new ResponseEntity<>(productListResponse, HttpStatus.OK);
    }


}
