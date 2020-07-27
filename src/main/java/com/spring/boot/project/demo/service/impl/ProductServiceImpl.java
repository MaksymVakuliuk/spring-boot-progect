package com.spring.boot.project.demo.service.impl;

import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.repository.ProductRepository;
import com.spring.boot.project.demo.service.ProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> saveAll(List<Product> productsList) {
        return productRepository.saveAll(productsList);
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<Product> iterable) {
        deleteAll(iterable);
    }

    @Override
    public List<Product> getMostCommentedProduct(int numberOfProducts) {
        return productRepository.findMostCommentedProduct(PageRequest.of(0, numberOfProducts));
    }
}
