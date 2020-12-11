package com.spring.boot.project.demo.controllers.unit;

import static org.junit.Assert.assertEquals;

import com.spring.boot.project.demo.controllers.ReviewController;
import com.spring.boot.project.demo.dto.product.ProductDto;
import com.spring.boot.project.demo.dto.review.ReviewFromDbDto;
import com.spring.boot.project.demo.dto.review.ReviewMapper;
import com.spring.boot.project.demo.dto.review.ReviewRequestDto;
import com.spring.boot.project.demo.dto.user.UserDto;
import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.service.ReviewService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReviewControllerTest {
    @InjectMocks
    private ReviewController reviewController;
    @Mock
    private ReviewService reviewService;
    @Mock
    private ReviewMapper reviewMapper;

    @Test
    public void getMostUsedWordsTest() {
        List<String> mostUsedWords = List.of("most", "used", "word");
        Mockito.when(reviewService.findMostUsedWords(2)).thenReturn(mostUsedWords);
        assertEquals(mostUsedWords, reviewController.getMostUsedWords(2));
    }

    @Test
    public void getAllTest() {
        Review review1 = new Review();
        review1.setId(1L);
        User user = new User();
        user.setUserId("userId1");
        user.setProfileName("userName1");
        review1.setUser(user);
        Product product = new Product();
        product.setProductId("productId1");
        review1.setProduct(product);
        review1.setHelpfulnessNumerator(1);
        review1.setHelpfulnessDenominator(1);
        review1.setScore(1);
        review1.setSummary("summary1");
        review1.setText("text1");
        ReviewFromDbDto reviewFromDbDto1 = new ReviewFromDbDto();
        reviewFromDbDto1.setId(1L);
        UserDto userDto = new UserDto();
        userDto.setUserId("userId1");
        userDto.setProfileName("userName1");
        reviewFromDbDto1.setUserDto(userDto);
        ProductDto productDto = new ProductDto();
        productDto.setProductId("productId1");
        reviewFromDbDto1.setProductDto(productDto);
        reviewFromDbDto1.setHelpfulnessNumerator(1);
        reviewFromDbDto1.setHelpfulnessDenominator(1);
        reviewFromDbDto1.setScore(1);
        reviewFromDbDto1.setSummary("summary1");
        reviewFromDbDto1.setText("text1");
        Mockito.when(reviewMapper.convertReviewToReviewFromDbDto(review1))
                .thenReturn(reviewFromDbDto1);
        Review review2 = new Review();
        review2.setId(2L);
        user = new User();
        user.setUserId("userId2");
        user.setProfileName("userName2");
        review2.setUser(user);
        product = new Product();
        product.setProductId("productId2");
        review2.setProduct(product);
        review2.setHelpfulnessNumerator(2);
        review2.setHelpfulnessDenominator(2);
        review2.setScore(2);
        review2.setSummary("summary2");
        review2.setText("text2");
        ReviewFromDbDto reviewFromDbDto2 = new ReviewFromDbDto();
        reviewFromDbDto2.setId(2L);
        userDto = new UserDto();
        userDto.setUserId("userId2");
        userDto.setProfileName("userName2");
        reviewFromDbDto2.setUserDto(userDto);
        productDto = new ProductDto();
        productDto.setProductId("productId2");
        reviewFromDbDto2.setProductDto(productDto);
        reviewFromDbDto2.setHelpfulnessNumerator(1);
        reviewFromDbDto2.setHelpfulnessDenominator(1);
        reviewFromDbDto2.setScore(1);
        reviewFromDbDto2.setSummary("summary2");
        reviewFromDbDto2.setText("text2");
        Mockito.when(reviewMapper.convertReviewToReviewFromDbDto(review2))
                .thenReturn(reviewFromDbDto2);
        Mockito.when(reviewService.findAll()).thenReturn(List.of(review1, review2));
        List<ReviewFromDbDto> reviewFromDbDtoList = List.of(reviewFromDbDto1, reviewFromDbDto2);
        assertEquals(reviewFromDbDtoList, reviewController.getAll());
    }

    @Test
    public void saveReviewTest() {
        ReviewRequestDto reviewRequestDto = new ReviewRequestDto();
        reviewRequestDto.setProductId("productId");
        reviewRequestDto.setHelpfulnessNumerator(1);
        reviewRequestDto.setHelpfulnessDenominator(1);
        reviewRequestDto.setScore(1);
        reviewRequestDto.setSummary("summary");
        reviewRequestDto.setText("text");
        Review review = new Review();
        Product product = new Product();
        product.setProductId("productId");
        review.setProduct(product);
        review.setHelpfulnessNumerator(1);
        review.setHelpfulnessDenominator(1);
        review.setScore(1);
        review.setSummary("summary");
        review.setText("text");
        Mockito.when(reviewMapper.convertReviewRequestDtoToReview(reviewRequestDto))
                .thenReturn(review);
        Review reviewFromDb = new Review();
        reviewFromDb.setId(1L);
        User user = new User();
        user.setUserId("userId");
        user.setProfileName("userName");
        reviewFromDb.setUser(user);
        reviewFromDb.setProduct(product);
        reviewFromDb.setHelpfulnessNumerator(1);
        reviewFromDb.setHelpfulnessDenominator(1);
        reviewFromDb.setScore(1);
        reviewFromDb.setSummary("summary");
        reviewFromDb.setText("text");
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
        Mockito.when(reviewService.save(review)).thenReturn(reviewFromDb);
        Mockito.when(reviewMapper.convertReviewToReviewFromDbDto(reviewFromDb))
                .thenReturn(reviewFromDbDto);
        ReviewFromDbDto result = reviewController.saveReview(reviewRequestDto);
        assertEquals(reviewFromDbDto, result);
    }

    @Test
    public void updateReviewTest() {
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
        Review reviewFromDb = new Review();
        reviewFromDb.setId(reviewFromDbDto.getId());
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setProfileName(userDto.getProfileName());
        reviewFromDb.setUser(user);
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        reviewFromDb.setProduct(product);
        Mockito.when(reviewMapper.convertReviewDbDtoToReview(reviewFromDbDto))
                .thenReturn(reviewFromDb);
        Review updatedReview = reviewFromDb;
        updatedReview.setSummary("updatedSummary");
        updatedReview.setText("updatedText");
        Mockito.when(reviewService.save(updatedReview)).thenReturn(updatedReview);
        ReviewFromDbDto updatedReviewFromDbDto = reviewFromDbDto;
        updatedReviewFromDbDto.setSummary(updatedReview.getSummary());
        updatedReviewFromDbDto.setText(updatedReview.getText());
        Mockito.when(reviewMapper.convertReviewToReviewFromDbDto(updatedReview))
                .thenReturn(updatedReviewFromDbDto);
        assertEquals(updatedReviewFromDbDto, reviewController.updateReview(reviewFromDbDto));
    }

    @Test
    public void deleteReviewTest() {
        assertEquals("Review with id 1 was deleted.", reviewController.deleteReview("1"));
    }
}
