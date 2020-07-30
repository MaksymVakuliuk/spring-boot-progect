package com.spring.boot.project.demo.service.impl;

import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.service.AmazonUserService;
import com.spring.boot.project.demo.service.DbService;
import com.spring.boot.project.demo.service.ProductService;
import com.spring.boot.project.demo.unit.FileReader;
import com.spring.boot.project.demo.unit.Parser;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewDataService implements DbService {
    private final FileReader fileReader;
    private final Parser<Review> parser;
    private final ReviewServiceImpl reviewService;
    private final ProductService productService;
    private final AmazonUserService amazonUserService;

    @Override
    public void initializeDb(String dataFilePath) {
        List<String> reviewsStringList = fileReader.readLines(dataFilePath);
        List<Review> reviewsList = parser.parse(reviewsStringList);
        reviewService.saveAll(reviewsList);
    }

    @Override
    public void clearDb() {
        reviewService.deleteAll();
        productService.deleteAll();
        amazonUserService.deleteAll();
    }
}
