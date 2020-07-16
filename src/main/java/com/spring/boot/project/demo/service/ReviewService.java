package com.spring.boot.project.demo.service;

import com.spring.boot.project.demo.model.AmazonUser;
import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.repository.AmazonUserRepository;
import com.spring.boot.project.demo.repository.ProductRepository;
import com.spring.boot.project.demo.repository.ReviewRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {
    private final AmazonUserRepository amazonUserRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public void saveAll(List<Review> reviewsList) {
        Set<AmazonUser> amazonUserSet = new HashSet<>();
        Set<Product> productSet = new HashSet<>();
        for (Review review : reviewsList) {
            amazonUserSet.add(review.getAmazonUser());
            productSet.add(review.getProduct());
        }
        addDataToDatabase(amazonUserSet, reviewsList, productSet);
    }

    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    private void addDataToDatabase(Iterable<AmazonUser> amazonUsers,
                                   Iterable<Review> reviews,
                                   Iterable<Product> products) {
        amazonUserRepository.saveAll(amazonUsers);
        productRepository.saveAll(products);
        reviewRepository.saveAll(reviews);
    }
}
