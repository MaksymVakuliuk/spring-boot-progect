package com.spring.boot.project.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.spring.boot.project.demo.dto.user.UserDto;
import com.spring.boot.project.demo.dto.user.UserMapper;
import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    private static final String REQUEST = "/users/most-active-users";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "USER")
    public void getMostActiveUserTest() throws Exception {
        User user1 = new User();
        user1.setUserId("userId1");
        user1.setProfileName("user1");
        User user2 = new User();
        user2.setUserId("userId2");
        user2.setProfileName("user2");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        UserDto userDto = new UserDto();
        userDto.setUserId("userId1");
        userDto.setProfileName("user1");
        UserDto userDto2 = new UserDto();
        userDto2.setUserId("userId2");
        userDto2.setProfileName("user2");
        Mockito.when(userService.findMostActiveUsers(2))
                .thenReturn(userList);
        Mockito.when(userMapper.convertToUserDto(userList.get(0)))
                .thenReturn(userDto);
        Mockito.when(userMapper.convertToUserDto(userList.get(1)))
                .thenReturn(userDto2);
        mockMvc.perform(get(REQUEST).param("numberOfUsers", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[1].userId", Matchers.is("userId2")));
    }
}
