package com.spring.boot.project.demo.controllers;

import com.spring.boot.project.demo.dto.amazonuser.AmazonUserDto;
import com.spring.boot.project.demo.dto.amazonuser.AmazonUserMapper;
import com.spring.boot.project.demo.service.AmazonUserService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amazon-users")
@AllArgsConstructor
public class AmazonUserController {
    private final AmazonUserService amazonUserService;
    private final AmazonUserMapper amazonUserMapper;

    @ApiOperation(value = "Finding most active users.",
            notes = "Default number of amazon users to find is 1000.")
    @GetMapping("/most-active-users")
    public List<AmazonUserDto> getMostActiveUser(
            @RequestParam(defaultValue = "1000") int numberOfAmazonUsers) {
        return amazonUserService.findMostActiveUsers(numberOfAmazonUsers).stream()
                .map(amazonUserMapper::convertToAmazonUserDto).collect(Collectors.toList());
    }
}