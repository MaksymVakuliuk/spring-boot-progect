package com.spring.boot.project.demo.controllers;

import com.spring.boot.project.demo.dto.user.UserRequestDto;
import com.spring.boot.project.demo.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @ApiOperation(value = "Users registrations.")
    @PostMapping("/log-up")
    public String logUp(@RequestBody UserRequestDto userRequestDto) {
        return authenticationService.logUp(userRequestDto) ? "You registered."
                : "Registration not successful.";
    }
}
