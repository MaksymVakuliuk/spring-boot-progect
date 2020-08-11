package com.spring.boot.project.demo.repository;

import static org.junit.Assert.assertEquals;

import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.service.DbService;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    private static final String REVIEWS_CSV_FILE_PATH
            = "src/test/resources/tests/util/reviews_test.csv";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DbService dbService;

    @Before
    public void setup() {
        dbService.clearDb();
        dbService.initializeDb(REVIEWS_CSV_FILE_PATH);
    }

    @Test
    public void getMostActiveUsers() {
        String expectedUsers = "user1, user2";
        List<User> mostActiveUsers
                = userRepository.findMostActiveUsers(PageRequest.of(0, 2));
        assertEquals(expectedUsers,
                mostActiveUsers.stream()
                        .map(User::getUserId)
                        .collect(Collectors.joining(", ")));
    }
}
