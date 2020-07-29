package com.spring.boot.project.demo.controllers;

import com.spring.boot.project.demo.dto.amazonuser.AmazonUserDto;
import com.spring.boot.project.demo.dto.amazonuser.AmazonUserMapper;
import com.spring.boot.project.demo.service.AmazonUserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amazonusers")
@AllArgsConstructor
public class AmazonUserController {
    private final AmazonUserService amazonUserService;
    private final AmazonUserMapper amazonUserMapper;

    @GetMapping("/mostactive/{numberOfAmazonUsers}")
    public List<AmazonUserDto> getMostActiveUser(@PathVariable int numberOfAmazonUsers) {
        return amazonUserService.findMostActiveUsers(numberOfAmazonUsers).stream()
                .map(amazonUserMapper::convertToAmazonUserDto).collect(Collectors.toList());
    }
}
