package com.spring.boot.project.demo.service;

import com.spring.boot.project.demo.model.User;

public interface UserService extends GenericService<User, Long> {
    User findByEmail(String email);
}
