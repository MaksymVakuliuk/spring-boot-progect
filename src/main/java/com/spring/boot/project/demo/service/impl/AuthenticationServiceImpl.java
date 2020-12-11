package com.spring.boot.project.demo.service.impl;

import com.spring.boot.project.demo.dto.user.UserMapper;
import com.spring.boot.project.demo.dto.user.UserRequestDto;
import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.repository.UserRepository;
import com.spring.boot.project.demo.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public boolean logUp(UserRequestDto userRequestDto) {
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        User user = userMapper.convertUserRequestDtoToUser(userRequestDto);
        return userRepository.save(user) != null;
    }
}
