package com.spring.boot.project.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void getAllText() {
        String expectedText = "[Review 1 text. First., Review 2 text. Second. Second., Review 3 text. Third., Review 4 text. Fourth., Review 5 text. Fifth.]";
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
