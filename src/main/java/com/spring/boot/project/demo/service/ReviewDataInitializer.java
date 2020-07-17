package com.spring.boot.project.demo.service;

import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.unit.FileReader;
import com.spring.boot.project.demo.unit.Parser;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewDataInitializer implements DbInitializer<Review> {
    private final FileReader fileReader;
    private final Parser<Review> parser;
    private final ReviewService reviewService;

    @Override
    public void initializeDb(String dataFilePath) {
        List<String> reviewsStringList = fileReader.readLines(dataFilePath);
        List<Review> reviewsList = parser.parse(reviewsStringList);
        reviewService.saveAll(reviewsList);
    }
}
