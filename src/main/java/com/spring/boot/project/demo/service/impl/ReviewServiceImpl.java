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
        addDataToDatabase(amazonUserSet, reviewsList, productSet);
        return reviewsList;
    }

    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow();
    }

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

    private void addDataToDatabase(Iterable<AmazonUser> amazonUsers,
                                   Iterable<Review> reviews,
                                   Iterable<Product> products) {
        amazonUserRepository.saveAll(amazonUsers);
        productRepository.saveAll(products);
        reviewRepository.saveAll(reviews);
    }

    @Override
    public List<String> getMostUsedWords(int numberOfWords) {
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
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return mostUsedWords;
    }
}
