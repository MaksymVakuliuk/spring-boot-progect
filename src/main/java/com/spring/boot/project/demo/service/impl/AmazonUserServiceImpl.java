package com.spring.boot.project.demo.service.impl;

import com.spring.boot.project.demo.model.AmazonUser;
import com.spring.boot.project.demo.repository.AmazonUserRepository;
import com.spring.boot.project.demo.service.AmazonUserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AmazonUserServiceImpl implements AmazonUserService {
    private final AmazonUserRepository amazonUserRepository;

    @Override
    public AmazonUser save(AmazonUser amazonUser) {
        return amazonUserRepository.save(amazonUser);
    }

    @Override
    public List<AmazonUser> saveAll(List<AmazonUser> amazonUsersList) {
        return amazonUserRepository.saveAll(amazonUsersList);
    }

    @Override
    public AmazonUser findById(String id) {
        return amazonUserRepository.findById(id).orElseThrow();
    }

    @Override
    public List<AmazonUser> findAll() {
        return amazonUserRepository.findAll();
    }

    @Override
    public void delete(AmazonUser amazonUser) {
        amazonUserRepository.delete(amazonUser);
    }

    @Override
    public void deleteById(String id) {
        amazonUserRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        amazonUserRepository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<AmazonUser> iterable) {
        amazonUserRepository.deleteAll(iterable);
    }

    @Override
    public List<AmazonUser> getMostActiveUsers(int numberOfAmazonUsers) {
        return amazonUserRepository.findMostActiveUsers(PageRequest.of(0, numberOfAmazonUsers));
    }
}
