package com.spring.boot.project.demo.controllers.unit;

import static org.junit.Assert.assertEquals;

import com.spring.boot.project.demo.controllers.UserController;
import com.spring.boot.project.demo.dto.user.UserDto;
import com.spring.boot.project.demo.dto.user.UserMapper;
import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.service.UserService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;

    @Test
    public void getMostActiveUserTest() {
        User user1 = new User();
        user1.setUserId("userId1");
        user1.setProfileName("user1");
        User user2 = new User();
        user2.setUserId("userId2");
        user2.setProfileName("user2");
        List<User> userList = List.of(user1, user2);
        Mockito.when(userService.findMostActiveUsers(2))
                .thenReturn(userList);
        UserDto userDto = new UserDto();
        userDto.setUserId("userId1");
        userDto.setProfileName("user1");
        UserDto userDto2 = new UserDto();
        userDto2.setUserId("userId2");
        userDto2.setProfileName("user2");
        List<UserDto> userDtoList = List.of(userDto, userDto2);
        Mockito.when(userMapper.convertToUserDto(user1))
                .thenReturn(userDto);
        Mockito.when(userMapper.convertToUserDto(user2))
                .thenReturn(userDto2);
        assertEquals(userDtoList, userController.getMostActiveUser(2));
    }
}
