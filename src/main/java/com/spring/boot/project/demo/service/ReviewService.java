package com.spring.boot.project.demo.service;

import com.spring.boot.project.demo.model.Review;
import java.util.List;

public interface ReviewService extends GenericService<Review, Long> {
    List<String> getMostUsedWords(int numberOfWords);
}
