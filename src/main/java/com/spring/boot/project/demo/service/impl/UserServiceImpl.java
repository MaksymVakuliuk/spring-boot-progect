package com.spring.boot.project.demo.service.impl;

import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.repository.UserRepository;
import com.spring.boot.project.demo.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> saveAll(List<User> listT) {
        return userRepository.saveAll(listT);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<User> iterable) {
        userRepository.deleteAll(iterable);
    }

    @Override
    public User findByProfileName(String profileName) {
        return userRepository.findByProfileName(profileName);
    }

    @Override
    public List<User> findMostActiveUsers(int numberOfUsers) {
        return userRepository.findMostActiveUsers(PageRequest.of(0, numberOfUsers));
    }
}
