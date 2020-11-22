package com.spring.boot.project.demo.controllers;

import com.spring.boot.project.demo.dto.user.UserDto;
import com.spring.boot.project.demo.dto.user.UserMapper;
import com.spring.boot.project.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @ApiOperation(value = "Finding most active users.",
            notes = "Default number of amazon users to find is 1000.")
    @GetMapping("/most-active-users")
    public List<UserDto> getMostActiveUser(
            @RequestParam(defaultValue = "1000") int numberOfUsers) {
        return userService.findMostActiveUsers(numberOfUsers).stream()
                .map(userMapper::convertToUserDto).collect(Collectors.toList());
    }
}
