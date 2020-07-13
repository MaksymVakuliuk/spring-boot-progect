package com.spring.boot.project.demo.unit;

import com.spring.boot.project.demo.dto.review.ParsedReviewDto;
import org.junit.Test;

import java.io.Reader;

import static org.junit.Assert.*;

public class ParseReviewCsvTest {
    private final FileReader fileReader = new FileUtil();
    private final Parse<ParsedReviewDto> parsedReviewDtoParse = new ParseReviewCsv();
    private final static String EXPECTED_REVIEW_TEST_PATH =
            "/home/max5akul/MateAcademy/spring-boot/src/test/resources/tests/utilTest/Expected_parsed_reviews_test.txt";
    private final static String REVIEW_TEST_PATH =
            "/home/max5akul/MateAcademy/spring-boot/src/test/resources/tests/utilTest/Reviews_test.csv";
    private final static String EMPTY_REVIEW_TEST_PATH =
            "/home/max5akul/MateAcademy/spring-boot/src/test/resources/tests/utilTest/Empty_reviews_test.csv";


    @Test
    public void parse() {
        Reader reader = new FileUtil().getReader(REVIEW_TEST_PATH);
        String expected = fileReader.readLines(EXPECTED_REVIEW_TEST_PATH).toString();
        assertEquals(expected, parsedReviewDtoParse.parse(reader).toString());
    }

    @Test
    public void readLinesOfEmptyFile() {
        Reader reader = new FileUtil().getReader(EMPTY_REVIEW_TEST_PATH);
        assertTrue(parsedReviewDtoParse.parse(reader).isEmpty());
    }

}