package com.spring.boot.project.demo.dto.review;

import com.spring.boot.project.demo.dto.product.ProductMapper;
import com.spring.boot.project.demo.dto.user.UserMapper;
import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.model.Review;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewMapper {
    private UserMapper userMapper;
    private ProductMapper productMapper;

    public ReviewFromDbDto convertToReviewFromDbDto(Review review) {
        ReviewFromDbDto reviewFromDbDto = new ReviewFromDbDto();
        reviewFromDbDto.setId(review.getId());
        reviewFromDbDto.setUserDto(userMapper
                .convertToUserDto(review.getUser()));
        reviewFromDbDto
                .setProductDto(productMapper.convertToUserDto(review.getProduct()));
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
        review.setUser(new UserMapper()
                .convertFromUserDto(parsedReviewDto.getUserDto()));
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
