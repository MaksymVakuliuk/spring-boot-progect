package com.spring.boot.project.demo.unit;

import com.spring.boot.project.demo.dto.amazonuser.AmazonUserDto;
import com.spring.boot.project.demo.dto.review.ParsedReviewDto;
import java.io.Reader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class ParseReviewCsv implements Parse<ParsedReviewDto> {
    private static final String PRODUCT_ID = "ProductId";
    private static final String USER_ID = "UserId";
    private static final String PROFILE_NAME = "ProfileName";
    private static final String HELPFULNESS_NUMERATOR = "HelpfulnessNumerator";
    private static final String HELPFULNESS_DENOMINATOR = "HelpfulnessDenominator";
    private static final String SCORE = "Score";
    private static final String TIME = "Time";
    private static final String SUMMARY = "Summary";
    private static final String TEXT = "Text";

    @SneakyThrows
    @Override
    public List<ParsedReviewDto> parse(Reader reader) {
        List<ParsedReviewDto> parsedReviewDtoList = new ArrayList<>();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        List<CSVRecord> records = csvParser.getRecords();
        for (CSVRecord record : records) {
            AmazonUserDto amazonUserDto = new AmazonUserDto();
            amazonUserDto.setUserId(record.get(USER_ID));
            amazonUserDto.setProfileName(PROFILE_NAME);
            ParsedReviewDto parsedReviewDto = new ParsedReviewDto();
            parsedReviewDto.setAmazonUserDto(amazonUserDto);
            parsedReviewDto.setProductId(record.get(PRODUCT_ID));
            parsedReviewDto.setHelpfulnessNumerator(
                    Integer.parseInt(record.get(HELPFULNESS_NUMERATOR)));
            parsedReviewDto.setHelpfulnessDenominator(
                    Integer.parseInt(record.get(HELPFULNESS_DENOMINATOR)));
            parsedReviewDto.setScore(Integer.parseInt(record.get(SCORE)));
            parsedReviewDto.setTime(LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(
                            Long.parseLong(record.get(TIME))), ZoneId.systemDefault()));
            parsedReviewDto.setSummary(record.get(SUMMARY));
            parsedReviewDto.setText(record.get(TEXT));
            parsedReviewDtoList.add(parsedReviewDto);
        }
        return parsedReviewDtoList;
    }
}
