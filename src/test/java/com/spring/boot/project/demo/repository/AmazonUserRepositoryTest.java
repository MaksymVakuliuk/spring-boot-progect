package com.spring.boot.project.demo.repository;

import com.spring.boot.project.demo.model.AmazonUser;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmazonUserRepositoryTest {
    @Autowired
    private AmazonUserRepository amazonUserRepository;

    @Test
    public void getMostActiveUsers() {
        String expectedUsers = "user1, user2";
        List<AmazonUser> mostActiveUsers = amazonUserRepository.findMostActiveUsers(PageRequest.of(0, 2));
        assertEquals(expectedUsers, mostActiveUsers.stream().map(user -> user.getUserId()).collect(Collectors.joining(", ")));
    }
}
