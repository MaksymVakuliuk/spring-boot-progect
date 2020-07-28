package com.spring.boot.project.demo.service;

import com.spring.boot.project.demo.model.Review;
import java.util.List;
import java.util.Set;

public interface ReviewService extends GenericService<Review, Long> {
    List<String> findMostUsedWords(int numberOfWords);

    Set<Review> findReviewsSetOfProduct(String productId);

    Set<Review> findReviewSetOfAmazonUser(String userId);
}
