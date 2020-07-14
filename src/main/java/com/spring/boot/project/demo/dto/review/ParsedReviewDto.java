package com.spring.boot.project.demo.dto.review;

import com.spring.boot.project.demo.dto.amazonuser.AmazonUserDto;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParsedReviewDto {
    private AmazonUserDto amazonUserDto;
    private String productId;
    private int helpfulnessNumerator;
    private int helpfulnessDenominator;
    private int score;
    private LocalDateTime time;
    private String summary;
    private String text;
}
