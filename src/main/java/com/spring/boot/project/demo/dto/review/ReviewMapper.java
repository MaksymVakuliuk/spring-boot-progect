package com.spring.boot.project.demo.dto.review;

import com.spring.boot.project.demo.dto.product.ProductMapper;
import com.spring.boot.project.demo.dto.user.UserMapper;
import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.service.ProductService;
import com.spring.boot.project.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewMapper {
    private UserMapper userMapper;
    private ProductMapper productMapper;
    private ProductService productService;
    private UserService userService;

    public ReviewFromDbDto convertReviewToReviewFromDbDto(Review review) {
        ReviewFromDbDto reviewFromDbDto = new ReviewFromDbDto();
        reviewFromDbDto.setId(review.getId());
        reviewFromDbDto.setUserDto(userMapper
                .convertToUserDto(review.getUser()));
        reviewFromDbDto
                .setProductDto(productMapper.convertToProductDto(review.getProduct()));
        reviewFromDbDto.setHelpfulnessNumerator(review.getHelpfulnessNumerator());
        reviewFromDbDto.setHelpfulnessDenominator(review.getHelpfulnessDenominator());
        reviewFromDbDto.setScore(review.getScore());
        reviewFromDbDto.setTime(review.getTime());
        reviewFromDbDto.setSummary(review.getSummary());
        reviewFromDbDto.setText(review.getText());
        return reviewFromDbDto;
    }

    public Review convertReviewRequestDtoToReview(ReviewRequestDto reviewRequestDto) {
        Review review = new Review();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByProfileName(authentication.getName());
        review.setUser(user);
        Product product = productService.findById(reviewRequestDto.getProductId());
        review.setProduct(product);
        review.setHelpfulnessNumerator(reviewRequestDto.getHelpfulnessNumerator());
        review.setHelpfulnessDenominator(reviewRequestDto.getHelpfulnessDenominator());
        review.setScore(reviewRequestDto.getScore());
        review.setSummary(reviewRequestDto.getSummary());
        review.setText(reviewRequestDto.getText());
        return review;
    }

    public Review convertReviewDbDtoToReview(ReviewFromDbDto reviewFromDbDto) {
        Review review = new Review();
        review.setId(reviewFromDbDto.getId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByProfileName(authentication.getName());
        review.setUser(user);
        Product product = productService.findById(reviewFromDbDto.getProductDto().getProductId());
        review.setProduct(product);
        review.setHelpfulnessNumerator(reviewFromDbDto.getHelpfulnessNumerator());
        review.setHelpfulnessDenominator(reviewFromDbDto.getHelpfulnessDenominator());
        review.setScore(reviewFromDbDto.getScore());
        review.setSummary(reviewFromDbDto.getSummary());
        review.setText(reviewFromDbDto.getText());
        return review;
    }
}
