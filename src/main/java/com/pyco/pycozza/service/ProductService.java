package com.pyco.pycozza.service;

import com.pyco.pycozza.model.Category;
import com.pyco.pycozza.model.Product;
import com.pyco.pycozza.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Page<Product> getProductList(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    public List<Product> getProductByCategory(int id) {
        return productRepository.findByCategoryId(id);
    }


    public void addNewProduct(int id, String name, int categoryId, String imgLink, String crust,
                              String size, int price, String description, int maxPrice) {
        if (productRepository.existsById(id)) {
            if (crust != null && size != null) {
                productRepository.save(new Product(id, name, categoryId, imgLink, crust, size, description, price, maxPrice));
            } else productRepository.save(new Product(id, name, categoryId, imgLink, price));
        }
    }

    public void addNewProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            productRepository.save(product);
        }
    }

    public Product findProductById(int id){
      return   productRepository.findById(id);
    }
}