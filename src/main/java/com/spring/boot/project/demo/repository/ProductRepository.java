package com.spring.boot.project.demo.repository;

import com.spring.boot.project.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
