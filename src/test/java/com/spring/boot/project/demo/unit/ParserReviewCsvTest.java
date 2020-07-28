package com.spring.boot.project.demo.unit;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import com.spring.boot.project.demo.model.AmazonUser;
import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.model.Review;
import org.junit.Assert;
import org.junit.Test;

public class ParserReviewCsvTest {
    private final FileReader fileReader = new FileUtil();
    private final Parser<Review> parsedReviewParser = new ParserReviewCsv();
    private final static String EXPECTED_PARSER_REVIEW_TEST_PATH =
            "src/test/resources/tests/util/expected_parser_reviews_test.csv";
    private final static String EMPTY_REVIEW_TEST_PATH =
            "src/test/resources/tests/util/empty_reviews_test.csv";

    @Test
    public void parseCsvLinesOfEmptyFile() {
        List<String> readLines = fileReader.readLines(EMPTY_REVIEW_TEST_PATH);
        Assert.assertTrue(parsedReviewParser.parse(readLines).isEmpty());
    }

    @Test
    public void parseCSVReviewFromStrings() {
        Review expectedReview1 = new Review();
        AmazonUser amazonUser1 = new AmazonUser();
        amazonUser1.setUserId("A3SGXH7AUHU8GW");
        amazonUser1.setProfileName("delmartian");
        expectedReview1.setAmazonUser(amazonUser1);
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
        AmazonUser amazonUser2 = new AmazonUser();
        amazonUser2.setUserId("A1D87F6ZCVE5NK");
        amazonUser2.setProfileName("dll pa");
        expectedReview2.setAmazonUser(amazonUser2);
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
