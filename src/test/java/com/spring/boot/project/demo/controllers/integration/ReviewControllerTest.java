package com.spring.boot.project.demo.controllers.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.project.demo.dto.product.ProductDto;
import com.spring.boot.project.demo.dto.review.ReviewFromDbDto;
import com.spring.boot.project.demo.dto.review.ReviewMapper;
import com.spring.boot.project.demo.dto.review.ReviewRequestDto;
import com.spring.boot.project.demo.dto.user.UserDto;
import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.service.DbService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReviewControllerTest {
    private static final String REVIEWS_CSV_FILE_PATH
            = "src/test/resources/tests/util/reviews_test.csv";
    private static final String GET_MOST_USER_WORDS_REQUEST = "/reviews/most-used-words";
    private static final String GET_ALL_REVIEWS_REQUEST = "/reviews/all";
    private static final String POST_SAVE_REVIEWS_REQUEST = "/reviews/save";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private DbService dbService;

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
        ReviewFromDbDto reviewFromDbDto = new ReviewFromDbDto();
        reviewFromDbDto.setId(2L);
        UserDto userDto = new UserDto();
        userDto.setProfileName("user");
        userDto.setUserId("testUserId");
        reviewFromDbDto.setUserDto(userDto);
        ProductDto productDto = new ProductDto();
        productDto.setProductId("product1");
        reviewFromDbDto.setProductDto(productDto);
        reviewFromDbDto.setHelpfulnessNumerator(2);
        reviewFromDbDto.setHelpfulnessDenominator(1);
        reviewFromDbDto.setScore(2);
        reviewFromDbDto.setSummary("testSummary");
        reviewFromDbDto.setText("testText");
        Review review = reviewMapper.convertReviewRequestDtoToReview(reviewRequestDto);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonReviewRequestDto = "";
        try {
            jsonReviewRequestDto = objectMapper.writer().writeValueAsString(reviewRequestDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        mockMvc.perform(post(POST_SAVE_REVIEWS_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReviewRequestDto))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.text", Matchers.is("testText")));
    }
}
