package com.spring.boot.project.demo.dto.user;

import static org.junit.Assert.assertEquals;

import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.model.UsersRole;
import com.spring.boot.project.demo.service.UsersRoleService;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {
    @InjectMocks
    private UserMapper userMapper;
    @Mock
    private UsersRoleService usersRoleService;

    @Test
    public void convertToUserDto() {
        User user = new User();
        user.setUserId("userId");
        user.setProfileName("user'sProfileName");
        user.setPassword("password");
        UsersRole usersRole = new UsersRole();
        usersRole.setRoleName(UsersRole.RoleName.USER);
        user.setRoles(Set.of(usersRole));
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setProfileName(user.getProfileName());
        assertEquals(userDto, userMapper.convertToUserDto(user));
    }

    @Test
    public void convertUserRequestDtoToUser() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setProfileName("user'sProfileName");
        userRequestDto.setPassword("password");
        User user = new User();
        user.setProfileName(userRequestDto.getProfileName());
        UsersRole usersRole = new UsersRole();
        usersRole.setRoleName(UsersRole.RoleName.USER);
        user.setRoles(Set.of(usersRole));
        user.setPassword(userRequestDto.getPassword());
        Mockito.when(usersRoleService.getRoleByName("USER")).thenReturn(usersRole);
        assertEquals(user, userMapper.convertUserRequestDtoToUser(userRequestDto));
    }
}
