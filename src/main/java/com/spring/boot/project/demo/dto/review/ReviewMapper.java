package com.spring.boot.project.demo.dto.review;

import com.spring.boot.project.demo.dto.amazonuser.AmazonUserMapper;
import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public ReviewFromDbDto convertToReviewFromDbDto(Review review) {
        ReviewFromDbDto reviewFromDbDto = new ReviewFromDbDto();
        reviewFromDbDto.setId(review.getId());
        reviewFromDbDto.setAmazonUserDto(new AmazonUserMapper()
                .convertToAmazonUserDto(review.getAmazonUser()));
        reviewFromDbDto.setProductId(review.getProduct().getProductId());
        reviewFromDbDto.setHelpfulnessNumerator(review.getHelpfulnessNumerator());
        reviewFromDbDto.setHelpfulnessDenominator(review.getHelpfulnessDenominator());
        reviewFromDbDto.setScore(review.getScore());
        reviewFromDbDto.setTime(review.getTime());
        reviewFromDbDto.setSummary(review.getSummary());
        reviewFromDbDto.setText(review.getText());

        return reviewFromDbDto;
    }

    public Review convertFromParsedReviewDto(ParsedReviewDto parsedReviewDto) {
        Review review = new Review();
        review.setAmazonUser(new AmazonUserMapper()
                .convertFromAmazonUserDto(parsedReviewDto.getAmazonUserDto()));
        Product product = new Product();
        product.setProductId(parsedReviewDto.getProductId());
        review.setProduct(product);
        review.setHelpfulnessNumerator(parsedReviewDto.getHelpfulnessNumerator());
        review.setHelpfulnessDenominator(parsedReviewDto.getHelpfulnessDenominator());
        review.setScore(parsedReviewDto.getScore());
        review.setTime(parsedReviewDto.getTime());
        review.setSummary(parsedReviewDto.getSummary());
        review.setText(parsedReviewDto.getText());
        return review;
    }
}

