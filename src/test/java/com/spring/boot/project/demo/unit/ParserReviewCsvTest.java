package com.spring.boot.project.demo.unit;

import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.model.User;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ParserReviewCsvTest {
    private static final String EXPECTED_PARSER_REVIEW_TEST_PATH =
            "src/test/resources/tests/util/expected_parser_reviews_test.csv";
    private static final String EMPTY_REVIEW_TEST_PATH =
            "src/test/resources/tests/util/empty_reviews_test.csv";
    private final FileReader fileReader = new FileUtil();
    private final Parser<Review> parsedReviewParser = new ParserReviewCsv();

    @Test
    public void parseCsvLinesOfEmptyFile() {
        List<String> readLines = fileReader.readLines(EMPTY_REVIEW_TEST_PATH);
        Assert.assertTrue(parsedReviewParser.parse(readLines).isEmpty());
    }

    @Test
    public void parseCsvReviewFromStrings() {
        Review expectedReview1 = new Review();
        User user1 = new User();
        user1.setUserId("A3SGXH7AUHU8GW");
        user1.setProfileName("delmartian");
        expectedReview1.setUser(user1);
        Product product1 = new Product();
        product1.setProductId("B001E4KFG0");
        expectedReview1.setProduct(product1);
        expectedReview1.setHelpfulnessNumerator(1);
        expectedReview1.setHelpfulnessDenominator(1);
        expectedReview1.setScore(5);
        expectedReview1.setTime(LocalDateTime.ofInstant(
                Instant.ofEpochMilli(
                        1303862400L), ZoneId.systemDefault()));
        expectedReview1.setSummary("Good Quality Dog Food");
        expectedReview1.setText("Review 1 text.");
        Review expectedReview2 = new Review();
        User user2 = new User();
        user2.setUserId("A1D87F6ZCVE5NK");
        user2.setProfileName("dll pa");
        expectedReview2.setUser(user2);
        Product product2 = new Product();
        product2.setProductId("B00813GRG4");
        expectedReview2.setProduct(product2);
        expectedReview2.setHelpfulnessNumerator(0);
        expectedReview2.setHelpfulnessDenominator(0);
        expectedReview2.setScore(1);
        expectedReview2.setTime(LocalDateTime.ofInstant(
                Instant.ofEpochMilli(1346976000L), ZoneId.systemDefault()));
        expectedReview2.setSummary("Not as Advertised");
        expectedReview2.setText("Review 2 text.");
        List<Review> expectedReviewsList = List.of(expectedReview1, expectedReview2);
        Assert.assertEquals(expectedReviewsList,
                parsedReviewParser.parse(fileReader.readLines(EXPECTED_PARSER_REVIEW_TEST_PATH)));
    }
}
