package com.spring.boot.project.demo.servise;

import com.spring.boot.project.demo.model.AmazonUser;
import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.repository.AmazonUserRepository;
import com.spring.boot.project.demo.repository.ProductRepository;
import com.spring.boot.project.demo.repository.ReviewRepository;
import com.spring.boot.project.demo.unit.FileReader;
import com.spring.boot.project.demo.unit.Parser;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewsToDbPutter implements DbPutter {
    private static final String REVIEW_CSV_FILE = "src/main/resources/csv/Reviews.csv";
    private final AmazonUserRepository amazonUserRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final Parser<Review> parser;
    private final FileReader fileReader;

    @Override
    public void putDataToDatabase() {
        List<String> reviewsStringList = fileReader.readLines(REVIEW_CSV_FILE);
        List<Review> reviewsList = parser.parse(reviewsStringList);
        Set<AmazonUser> amazonUserSet = new HashSet<>();
        Set<Product> productSet = new HashSet<>();
        for (Review review : reviewsList) {
            amazonUserSet.add(review.getAmazonUser());
            productSet.add(review.getProduct());
        }
        addDataToDatabase(amazonUserSet, reviewsList, productSet);
    }

    private void addDataToDatabase(Iterable<AmazonUser> amazonUsers,
                                   Iterable<Review> reviews,
                                   Iterable<Product> products) {
        amazonUserRepository.saveAll(amazonUsers);
        productRepository.saveAll(products);
        reviewRepository.saveAll(reviews);
    }
}
