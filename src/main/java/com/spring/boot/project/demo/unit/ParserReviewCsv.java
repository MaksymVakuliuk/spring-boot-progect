package com.spring.boot.project.demo.unit;

import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.model.User;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class ParserReviewCsv implements Parser<Review> {
    private static final int PRODUCT_ID = 1;
    private static final int USER_ID = 2;
    private static final int PROFILE_NAME = 3;
    private static final int HELPFULNESS_NUMERATOR = 4;
    private static final int HELPFULNESS_DENOMINATOR = 5;
    private static final int SCORE = 6;
    private static final int TIME = 7;
    private static final int SUMMARY = 8;
    private static final int TEXT = 9;

    @Override
    public List<Review> parse(List<String> reviews) {
        List<Review> reviewList = new ArrayList<>();
        reviews.remove(0);
        for (String reviewString : reviews) {
            CSVParser csvParser = null;
            try {
                csvParser = CSVParser.parse(reviewString, CSVFormat.DEFAULT);
            } catch (IOException ioException) {
                throw new RuntimeException("Can't get CSVParser: ", ioException);
            }
            CSVRecord record = null;
            try {
                record = csvParser.getRecords().get(0);
            } catch (IOException ioException) {
                throw new RuntimeException("Can't get CSVRecords from CSVParser:  ", ioException);
            }
            reviewList.add(getReviewFromRecord(record));
        }
        return reviewList;
    }

    private Review getReviewFromRecord(CSVRecord record) {
        Review review = new Review();
        User user = new User();
        user.setUserId(record.get(USER_ID));
        user.setProfileName(record.get(PROFILE_NAME));
        review.setUser(user);
        Product product = new Product();
        product.setProductId(record.get(PRODUCT_ID));
        review.setProduct(product);
        review.setHelpfulnessNumerator(
                Integer.parseInt(record.get(HELPFULNESS_NUMERATOR)));
        review.setHelpfulnessDenominator(
                Integer.parseInt(record.get(HELPFULNESS_DENOMINATOR)));
        review.setScore(Integer.parseInt(record.get(SCORE)));
        review.setTime(LocalDateTime.ofInstant(
                Instant.ofEpochMilli(Long.parseLong(record.get(TIME))), ZoneId.systemDefault()));
        review.setSummary(record.get(SUMMARY));
        review.setText(record.get(TEXT));
        return review;
    }
}
