package com.spring.boot.project.demo.service;

import com.spring.boot.project.demo.model.AmazonUser;
import java.util.List;

public interface AmazonUserService extends GenericService<AmazonUser, String> {
    List<AmazonUser> findMostActiveUsers(int numberOfAmazonUsers);
}
