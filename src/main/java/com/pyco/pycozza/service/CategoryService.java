package com.pyco.pycozza.service;


import com.pyco.pycozza.model.Category;
import com.pyco.pycozza.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getList(){
        return categoryRepository.findAll();
    }

//    public Category getCategoryById(int id){
//        return categoryRepository.findById(id);
//    }

    public Category getCategoryByKey(String key){
        return categoryRepository.findCategoriesByNameContaining(key);
    }

    public Category addNewCategory(int id, String name){
        if (categoryRepository.findById(id) != null){
            Category category = new Category(id, name);
            return categoryRepository.save(category);
        }
        else return null;

    }









}
