package com.spring.boot.project.demo.repository;

import com.spring.boot.project.demo.model.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r.text from Review r")
    List<String> getAllText();
}
