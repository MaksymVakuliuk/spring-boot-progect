package com.spring.boot.project.demo.controllers;

import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.service.DbService;
import com.spring.boot.project.demo.service.ReviewService;
import com.spring.boot.project.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InjectController {
    private static final String REVIEWS_CSV_FILE_PATH
            = "src/main/resources/csv/Reviews.csv";
    private final UserService userService;
    private final ReviewService reviewService;
    private final DbService dbService;

    @ApiOperation(value = "Inserting data from data to DB.")
    @GetMapping("/inject-reviews-to-db")
    public String insertReviewsToDb() {
        dbService.initializeDb(REVIEWS_CSV_FILE_PATH);
        return "Success inject data to db.";
    }

    @ApiOperation(value = "Inserting test data from data to DB.")
    @GetMapping("/inject-test-reviews-to-db")
    public String insertTestReviewsToDb() {
        dbService.initializeDb("src/test/resources/tests/util/reviews_test.csv");
        return "Success inject test data to db.";
    }

    @ApiOperation(value = "Inserting test review to DB.")
    @PostMapping("/inject-reviews-to-db")
    public String insertTestReviewsToDb(Authentication authentication) {
        Review review = new Review();
        User user = userService.findByProfileName(authentication.getName());
        review.setUser(user);
        Product product = new Product();
        product.setProductId("product2");
        review.setProduct(product);
        review.setHelpfulnessNumerator(1);
        review.setHelpfulnessDenominator(1);
        review.setScore(5);
        review.setTime(LocalDateTime.now());
        review.setSummary("my users");
        review.setText("Review from authorized user text.");
        reviewService.save(review);
        return "Success inject test review to db.";
    }
}
