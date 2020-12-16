package com.spring.boot.project.demo.dto.review;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewRequestDto {
    private String productId;
    private int helpfulnessNumerator;
    private int helpfulnessDenominator;
    private int score;
    private String summary;
    private String text;
}
