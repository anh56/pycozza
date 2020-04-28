package com.pyco.pycozza.controller;


import com.pyco.pycozza.model.Category;
import com.pyco.pycozza.service.CategoryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("")
     public List<Category> getList(){
        return categoryService.getList();
    }

//    @GetMapping("/{id}")
//    public Optional<Category> getCategoryById(@PathVariable int id){
//        return categoryService.getCategoryById(id);
//    }

//    @GetMapping("/{key}")
//    public Category getCategoryByKey(@PathVariable String key){
//        return categoryService.getCategoryByKey(key);
//    }

    @PostMapping("/add")
    public Category addNewCategory( @RequestParam int id, @RequestParam String name){
        return categoryService.addNewCategory(id, name);
    }




}
