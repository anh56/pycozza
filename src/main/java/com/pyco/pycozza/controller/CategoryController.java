package com.pyco.pycozza.controller;


import com.pyco.pycozza.api.CategoryApi;
import com.pyco.pycozza.api.model.CategoryListResponse;
import com.pyco.pycozza.model.Category;
import com.pyco.pycozza.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController implements CategoryApi{

    @Autowired
    private CategoryService categoryService;



    @Override
    public ResponseEntity<CategoryListResponse> getCategory() {
        return null;
    }

    @Override
    public ResponseEntity<CategoryListResponse> getCategoryById(@NotNull @Valid Integer id) {
        return null;
    }




    // old
    @GetMapping("/v1/{key}")
    public Category getCategoryByKey(@PathVariable String key){
        return categoryService.getCategoryByKey(key);
    }

    //old
    @PostMapping("/v1/add")
    public Category addNewCategory( @RequestParam int id, @RequestParam String name){
        return categoryService.addNewCategory(id, name);
    }

}
