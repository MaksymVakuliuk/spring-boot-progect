package com.spring.boot.project.demo.repository;

import com.spring.boot.project.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
