package com.pyco.pycozza.controller;


import com.pyco.pycozza.api.CategoryApi;
import com.pyco.pycozza.api.model.CategoryListResponse;
import com.pyco.pycozza.api.model.CategoryResponseModel;
import com.pyco.pycozza.model.Category;
import com.pyco.pycozza.service.CategoryService;
import com.pyco.pycozza.util.ActionAspect;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS})
public class CategoryController implements CategoryApi{
    private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<CategoryListResponse> getCategory() {
        List<Category> categoryList = categoryService.getList();
        return buildCategoryListResponse(categoryList);
    }

    public ResponseEntity<CategoryListResponse> buildCategoryListResponse(List<Category> categoryList){
        CategoryListResponse categoryListResponse = new CategoryListResponse();
        if (categoryList != null){
            categoryList.forEach(item -> categoryListResponse.addCategoriesItem(modelMapper.map(item, CategoryResponseModel.class)));
        }
        return new ResponseEntity<>(categoryListResponse, HttpStatus.OK);
    }


//
//    // old
//    @GetMapping("/v1/{key}")
//    public Category getCategoryByKey(@PathVariable String key){
//        return categoryService.getCategoryByKey(key);
//    }
//
//    //old
//    @PostMapping("/v1/add")
//    public Category addNewCategory( @RequestParam int id, @RequestParam String name){
//        return categoryService.addNewCategory(id, name);
//    }

}
