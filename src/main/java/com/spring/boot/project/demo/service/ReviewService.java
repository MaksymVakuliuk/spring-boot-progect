package com.spring.boot.project.demo.service;

import com.spring.boot.project.demo.model.Review;
import java.util.List;
import java.util.Set;

public interface ReviewService extends GenericService<Review, Long> {
    List<String> getMostUsedWords(int numberOfWords);

    Set<Review> getReviewsSetOfProduct(String productId);

    Set<Review> getReviewSetOfAmazonUser(String userId);
}
