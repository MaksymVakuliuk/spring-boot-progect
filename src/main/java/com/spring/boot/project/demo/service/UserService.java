package com.spring.boot.project.demo.service;

import com.spring.boot.project.demo.model.User;
import java.util.List;

public interface UserService extends GenericService<User, String> {
    List<User> findMostActiveUsers(int numberOfUsers);

    User findByProfileName(String profileName);
}
