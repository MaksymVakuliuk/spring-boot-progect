package com.spring.boot.project.demo.repository;

import com.spring.boot.project.demo.model.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r.text from Review r")
    List<String> getAllText();

    @Query("select r from Review r "
            + "left join fetch r.amazonUser a "
            + "left join fetch r.product p")
    List<Review> findAll();

    @Query("select r from Review r "
            + "left join fetch r.product p "
            + "where r.amazonUser.userId = ?1")
    List<Review> findByAmazonUser_UserId(String userId);

    @Query("select r from Review r "
            + "left join fetch r.amazonUser a "
            + "where r.product.productId = ?1")
    List<Review> findByProduct_ProductId(String productId);
}
