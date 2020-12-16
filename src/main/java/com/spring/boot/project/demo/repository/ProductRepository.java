package com.spring.boot.project.demo.repository;

import com.spring.boot.project.demo.model.Product;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("select p from Product p "
            + "order by size(p.reviews) desc, p.productId")
    List<Product> findMostCommentedProduct(Pageable pageable);
}
