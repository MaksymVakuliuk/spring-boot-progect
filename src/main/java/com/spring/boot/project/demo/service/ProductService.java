package com.spring.boot.project.demo.service;

import com.spring.boot.project.demo.model.Product;
import java.util.List;

public interface ProductService extends GenericService<Product, String> {
    List<Product> findMostCommentedProduct(int numberOfProducts);
}
