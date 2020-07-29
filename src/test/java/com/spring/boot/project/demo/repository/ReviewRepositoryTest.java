package com.spring.boot.project.demo.repository;

import com.spring.boot.project.demo.service.DbService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewRepositoryTest {
    private static final String REVIEWS_CSV_FILE_PATH
            = "src/test/resources/tests/util/reviews_test.csv";
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private DbService dbService;

    @Before
    public void setup() {
        dbService.clearDb();
        dbService.initializeDb(REVIEWS_CSV_FILE_PATH);
    }

    @Test
    public void getAllText() {
        String expectedText = "[Review 1 text. First., " +
                "Review 2 text. Second. Second., " +
                "Review 3 text. Third., " +
                "Review 4 text. Fourth., " +
                "Review 5 text. Fifth.]";
        assertEquals(expectedText, reviewRepository.getAllText().toString());
    }

    @Test
    public void findAll() {
        assertEquals(5, reviewRepository.findAll().size());
    }

    @Test
    public void findByAmazonUser_UserId() {
        assertEquals(2, reviewRepository.findByAmazonUser_UserId("user1").size());
    }

    @Test
    public void findByProduct_ProductId() {
        assertEquals(3, reviewRepository.findByProduct_ProductId("product1").size());
    }
}
