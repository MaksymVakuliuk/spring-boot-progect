package com.spring.boot.project.demo.service.impl;

import com.spring.boot.project.demo.model.AmazonUser;
import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.repository.AmazonUserRepository;
import com.spring.boot.project.demo.repository.ProductRepository;
import com.spring.boot.project.demo.repository.ReviewRepository;
import com.spring.boot.project.demo.service.ReviewService;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final AmazonUserRepository amazonUserRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> saveAll(List<Review> reviewsList) {
        Set<AmazonUser> amazonUserSet = new HashSet<>();
        Set<Product> productSet = new HashSet<>();
        for (Review review : reviewsList) {
            amazonUserSet.add(review.getAmazonUser());
            productSet.add(review.getProduct());
        }
        amazonUserRepository.saveAll(amazonUserSet);
        productRepository.saveAll(productSet);
        reviewRepository.saveAll(reviewsList);
        return reviewsList;
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public void delete(Review review) {
        reviewRepository.delete(review);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        reviewRepository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<Review> iterable) {
        reviewRepository.deleteAll(iterable);
    }

    @Override
    public List<String> findMostUsedWords(int numberOfWords) {
        List<String> allWords = reviewRepository.getAllText()
                .stream()
                .flatMap(text -> Arrays.stream(text.toLowerCase().split("[^a-z]+")))
                .collect(Collectors.toList());
        Map<String, Integer> numberOfWord = new HashMap<>();
        for (String word : allWords) {
            numberOfWord.merge(word, 1, Integer::sum);
        }
        List<String> mostUsedWords = numberOfWord.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(numberOfWords)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return mostUsedWords;
    }

    @Override
    public Set<Review> findReviewsSetOfProduct(String productId) {
        List<Review> byProductId = reviewRepository.findByProduct_ProductId(productId);
        return byProductId.stream()
                .map(review -> {
                    review.setProduct(null);
                    review.getAmazonUser().setReviews(null);
                    return review;
                })
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Review> findReviewSetOfAmazonUser(String userId) {
        List<Review> byAmazonUserId = reviewRepository.findByAmazonUser_UserId(userId);
        return byAmazonUserId.stream()
                .map(review -> {
                    review.setAmazonUser(null);
                    review.getProduct().setReviews(null);
                    return review;
                })
                .collect(Collectors.toSet());
    }
}
