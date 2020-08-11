package com.spring.boot.project.demo.dto.user;

import com.spring.boot.project.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setProfileName(user.getProfileName());
        return userDto;
    }

    public User convertFromUserDto(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setProfileName(userDto.getProfileName());
        return user;
    }
}

