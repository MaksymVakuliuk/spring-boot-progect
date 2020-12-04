package com.spring.boot.project.demo.dto.review;

import static org.junit.Assert.assertEquals;

import com.spring.boot.project.demo.dto.product.ProductDto;
import com.spring.boot.project.demo.dto.product.ProductMapper;
import com.spring.boot.project.demo.dto.user.UserDto;
import com.spring.boot.project.demo.dto.user.UserMapper;
import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.service.ProductService;
import com.spring.boot.project.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

@RunWith(MockitoJUnitRunner.class)
public class ReviewMapperTest {
    @InjectMocks
    private ReviewMapper reviewMapper;
    @Mock
    private UserMapper userMapper;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private ProductService productService;
    @Mock
    private UserService userService;
    @Mock
    private Authentication authentication;
    @Mock
    private SecurityContext securityContext;

    @Test
    public void convertReviewToReviewFromDbDto() {
        Review review = new Review();
        review.setId(1L);
        User user = new User();
        user.setUserId("userId");
        user.setProfileName("userName");
        review.setUser(user);
        Product product = new Product();
        product.setProductId("productId");
        review.setProduct(product);
        review.setHelpfulnessNumerator(1);
        review.setHelpfulnessDenominator(1);
        review.setScore(1);
        review.setSummary("summary");
        review.setText("text");
        ReviewFromDbDto reviewFromDbDto = new ReviewFromDbDto();
        reviewFromDbDto.setId(1L);
        UserDto userDto = new UserDto();
        userDto.setUserId("userId");
        userDto.setProfileName("userName");
        reviewFromDbDto.setUserDto(userDto);
        ProductDto productDto = new ProductDto();
        productDto.setProductId("productId");
        reviewFromDbDto.setProductDto(productDto);
        reviewFromDbDto.setHelpfulnessNumerator(1);
        reviewFromDbDto.setHelpfulnessDenominator(1);
        reviewFromDbDto.setScore(1);
        reviewFromDbDto.setSummary("summary");
        reviewFromDbDto.setText("text");
        Mockito.when(userMapper.convertToUserDto(user)).thenReturn(userDto);
        Mockito.when(productMapper.convertToProductDto(product)).thenReturn(productDto);
        assertEquals(reviewFromDbDto, reviewMapper.convertReviewToReviewFromDbDto(review));
    }

    @Test
    @WithMockUser(username = "userName")
    public void convertReviewRequestDtoToReview() {
        ReviewRequestDto reviewRequestDto = new ReviewRequestDto();
        reviewRequestDto.setProductId("productId");
        reviewRequestDto.setHelpfulnessNumerator(1);
        reviewRequestDto.setHelpfulnessDenominator(1);
        reviewRequestDto.setScore(1);
        reviewRequestDto.setSummary("summary");
        reviewRequestDto.setText("text");
        Review review = new Review();
        User user = new User();
        user.setUserId("userId");
        user.setProfileName("userName");
        review.setUser(user);
        Product product = new Product();
        product.setProductId("productId");
        review.setProduct(product);
        review.setHelpfulnessNumerator(1);
        review.setHelpfulnessDenominator(1);
        review.setScore(1);
        review.setSummary("summary");
        review.setText("text");
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getName()).thenReturn("userName");
        Mockito.when(userService.findByProfileName("userName")).thenReturn(user);
        Mockito.when(productService.findById("productId")).thenReturn(product);
        assertEquals(review, reviewMapper.convertReviewRequestDtoToReview(reviewRequestDto));
    }

    @Test
    public void convertReviewDbDtoToReview() {
        ReviewFromDbDto reviewFromDbDto = new ReviewFromDbDto();
        reviewFromDbDto.setId(1L);
        UserDto userDto = new UserDto();
        userDto.setUserId("userId");
        userDto.setProfileName("userName");
        reviewFromDbDto.setUserDto(userDto);
        ProductDto productDto = new ProductDto();
        productDto.setProductId("productId");
        reviewFromDbDto.setProductDto(productDto);
        reviewFromDbDto.setHelpfulnessNumerator(1);
        reviewFromDbDto.setHelpfulnessDenominator(1);
        reviewFromDbDto.setScore(1);
        reviewFromDbDto.setSummary("summary");
        reviewFromDbDto.setText("text");
        Review review = new Review();
        review.setId(1L);
        User user = new User();
        user.setUserId("userId");
        user.setProfileName("userName");
        review.setUser(user);
        Product product = new Product();
        product.setProductId("productId");
        review.setProduct(product);
        review.setHelpfulnessNumerator(1);
        review.setHelpfulnessDenominator(1);
        review.setScore(1);
        review.setSummary("summary");
        review.setText("text");
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getName()).thenReturn("userName");
        Mockito.when(userService.findByProfileName("userName")).thenReturn(user);
        Mockito.when(productService.findById("productId")).thenReturn(product);
        assertEquals(review, reviewMapper.convertReviewDbDtoToReview(reviewFromDbDto));
    }
}
