package com.spring.boot.project.demo.dto.review;

import com.spring.boot.project.demo.dto.amazonuser.AmazonUserDto;
import com.spring.boot.project.demo.dto.product.ProductDto;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewFromDbDto {
    private Long id;
    private AmazonUserDto amazonUserDto;
    private ProductDto productDto;
    private int helpfulnessNumerator;
    private int helpfulnessDenominator;
    private int score;
    private LocalDateTime time;
    private String summary;
    private String text;
}
