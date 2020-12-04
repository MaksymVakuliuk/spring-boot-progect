package com.spring.boot.project.demo.controllers.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.project.demo.dto.review.ReviewFromDbDto;
import com.spring.boot.project.demo.dto.review.ReviewMapper;
import com.spring.boot.project.demo.dto.review.ReviewRequestDto;
import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.service.DbService;
import com.spring.boot.project.demo.service.ReviewService;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {
    private static final String REVIEWS_CSV_FILE_PATH
            = "src/test/resources/tests/util/reviews_test.csv";
    private static final String GET_MOST_USER_WORDS_REQUEST = "/reviews/most-used-words";
    private static final String GET_ALL_REVIEWS_REQUEST = "/reviews/all";
    private static final String POST_SAVE_REVIEW_REQUEST = "/reviews/save";
    private static final String POST_UPDATE_REVIEW_REQUEST = "/reviews/update";
    private static final String GET_DELETE_REVIEW_REQUEST = "/reviews/remove/";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DbService dbService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewMapper reviewMapper;

    @Before
    public void inject() {
        dbService.initializeDb(REVIEWS_CSV_FILE_PATH);
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "USER")
    public void getMostUsedWords() throws Exception {
        mockMvc.perform(get(GET_MOST_USER_WORDS_REQUEST).param("numberOfWords", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0]", Matchers.is("review")))
                .andExpect(jsonPath("$[1]", Matchers.is("text")));
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    public void getAll() throws Exception {
        mockMvc.perform(get(GET_ALL_REVIEWS_REQUEST))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(6)))
                .andExpect(jsonPath("$[0].summary", Matchers.is("Good Quality Dog Food")))
                .andExpect(jsonPath("$[1].text", Matchers.is("Review 2 text. Second.")))
                .andExpect(jsonPath("$[5].text", Matchers.is("Review 6 text. Sixth.")));
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "USER")
    public void saveReview() throws Exception {
        ReviewRequestDto reviewRequestDto = new ReviewRequestDto();
        reviewRequestDto.setProductId("product1");
        reviewRequestDto.setHelpfulnessNumerator(2);
        reviewRequestDto.setHelpfulnessDenominator(1);
        reviewRequestDto.setScore(2);
        reviewRequestDto.setSummary("testSummary");
        reviewRequestDto.setText("testText");
        String jsonReviewRequestDto = "";
        try {
            jsonReviewRequestDto = new ObjectMapper().writer().writeValueAsString(reviewRequestDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        mockMvc.perform(post(POST_SAVE_REVIEW_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReviewRequestDto))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.notNullValue()))
                .andExpect(jsonPath("$.helpfulnessNumerator", Matchers.is(2)))
                .andExpect(jsonPath("$.helpfulnessDenominator", Matchers.is(1)))
                .andExpect(jsonPath("$.score", Matchers.is(2)))
                .andExpect(jsonPath("$.productDto.productId", Matchers.is("product1")))
                .andExpect(jsonPath("$.userDto.profileName", Matchers.is("user")))
                .andExpect(jsonPath("$.text", Matchers.is("testText")));
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "USER")
    public void updateReviewTest() throws Exception {
        ReviewRequestDto reviewRequestDto = new ReviewRequestDto();
        reviewRequestDto.setProductId("product1");
        reviewRequestDto.setHelpfulnessNumerator(2);
        reviewRequestDto.setHelpfulnessDenominator(1);
        reviewRequestDto.setScore(2);
        reviewRequestDto.setSummary("testSummary");
        reviewRequestDto.setText("testText");
        Review review = reviewMapper.convertReviewRequestDtoToReview(reviewRequestDto);
        review.setTime(LocalDateTime.now());
        reviewService.save(review);
        review.setText("Updated summary.");
        review.setTime(null);
        ReviewFromDbDto reviewFromDbDto = reviewMapper.convertReviewToReviewFromDbDto(review);
        String jsonReviewFromDbDto = "";
        try {
            jsonReviewFromDbDto = new ObjectMapper().writer().writeValueAsString(reviewFromDbDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        mockMvc.perform(post(POST_UPDATE_REVIEW_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReviewFromDbDto))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.notNullValue()))
                .andExpect(jsonPath("$.helpfulnessNumerator", Matchers.is(2)))
                .andExpect(jsonPath("$.helpfulnessDenominator", Matchers.is(1)))
                .andExpect(jsonPath("$.score", Matchers.is(2)))
                .andExpect(jsonPath("$.productDto.productId", Matchers.is("product1")))
                .andExpect(jsonPath("$.userDto.profileName", Matchers.is("user")))
                .andExpect(jsonPath("$.text", Matchers.is("Updated summary.")));
    }

    @Test(expected = NoSuchElementException.class)
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    public void deleteReviewTest() throws Exception {
        ReviewRequestDto reviewRequestDto = new ReviewRequestDto();
        reviewRequestDto.setProductId("product1");
        reviewRequestDto.setHelpfulnessNumerator(2);
        reviewRequestDto.setHelpfulnessDenominator(1);
        reviewRequestDto.setScore(2);
        reviewRequestDto.setSummary("testSummary");
        reviewRequestDto.setText("testText");
        Review review = reviewMapper.convertReviewRequestDtoToReview(reviewRequestDto);
        review.setTime(LocalDateTime.now());
        reviewService.save(review);
        assertNotNull(reviewService.findById(review.getId()));
        mockMvc.perform(get(GET_DELETE_REVIEW_REQUEST + review.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        Matchers.is("Review with id " + review.getId() + " was deleted.")));
        assertNull(reviewService.findById(review.getId()).getId());
    }
}
