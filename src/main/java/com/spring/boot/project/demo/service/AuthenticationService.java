package com.spring.boot.project.demo.service;

import com.spring.boot.project.demo.dto.user.UserRequestDto;

public interface AuthenticationService {
    boolean logUp(UserRequestDto userRequestDto);
}
