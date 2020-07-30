package com.spring.boot.project.demo.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.spring.boot.project.demo.dto.amazonuser.AmazonUserDto;
import com.spring.boot.project.demo.dto.amazonuser.AmazonUserMapper;
import com.spring.boot.project.demo.model.AmazonUser;
import com.spring.boot.project.demo.service.AmazonUserService;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(AmazonUserController.class)
public class AmazonUserControllerTest {
    private static final String REQUEST = "/amazonusers/mostactive";
    @MockBean
    private AmazonUserService amazonUserService;
    @MockBean
    private AmazonUserMapper amazonUserMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", password = "pass")
    public void getMostActiveUserTest() throws Exception {
        AmazonUser amazonUser1 = new AmazonUser();
        amazonUser1.setUserId("userId1");
        amazonUser1.setProfileName("user1");
        AmazonUser amazonUser2 = new AmazonUser();
        amazonUser2.setUserId("userId2");
        amazonUser2.setProfileName("user2");
        List<AmazonUser> amazonUserList = new ArrayList<>();
        amazonUserList.add(amazonUser1);
        amazonUserList.add(amazonUser2);
        AmazonUserDto amazonUserDto1 = new AmazonUserDto();
        amazonUserDto1.setUserId("userId1");
        amazonUserDto1.setProfileName("user1");
        AmazonUserDto amazonUserDto2 = new AmazonUserDto();
        amazonUserDto2.setUserId("userId2");
        amazonUserDto2.setProfileName("user2");
        Mockito.when(amazonUserService.findMostActiveUsers(2))
                .thenReturn(amazonUserList);
        Mockito.when(amazonUserMapper.convertToAmazonUserDto(amazonUserList.get(0)))
                .thenReturn(amazonUserDto1);
        Mockito.when(amazonUserMapper.convertToAmazonUserDto(amazonUserList.get(1)))
                .thenReturn(amazonUserDto2);
        mockMvc.perform(MockMvcRequestBuilders.get(REQUEST).param("numberOfAmazonUsers", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[1].userId", Matchers.is("userId2")));
    }
}
