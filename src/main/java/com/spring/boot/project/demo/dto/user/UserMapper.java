package com.spring.boot.project.demo.dto.user;

import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.service.UsersRoleService;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
    private static final String USER_ROLE = "USER";
    private UsersRoleService usersRoleService;

    public UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setProfileName(user.getProfileName());
        return userDto;
    }

    public User convertUserRequestDtoToUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setProfileName(userRequestDto.getProfileName());
        user.setRoles(Set.of(usersRoleService.getRoleByName(USER_ROLE)));
        user.setPassword(userRequestDto.getPassword());
        return user;
    }
}
