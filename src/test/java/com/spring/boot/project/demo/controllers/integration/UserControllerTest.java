package com.spring.boot.project.demo.controllers.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    private static final String REVIEWS_CSV_FILE_PATH
            = "src/test/resources/tests/util/reviews_test.csv";
    private static final String REQUEST = "/users/most-active-users";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DbService dbService;

    @Before
    public void inject() {
        dbService.initializeDb(REVIEWS_CSV_FILE_PATH);
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "USER")
    public void getMostActiveUserTest() throws Exception {
        mockMvc.perform(get(REQUEST).param("numberOfUsers", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].userId", Matchers.is("user1")))
                .andExpect(jsonPath("$[1].userId", Matchers.is("user2")));
    }
}
