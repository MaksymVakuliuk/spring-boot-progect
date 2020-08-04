package com.spring.boot.project.demo.service.impl;

import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.repository.UserRepository;
import com.spring.boot.project.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User save(User User) {
        return userRepository.save(User);
    }

    @Override
    public List<User> saveAll(List<User> listT) {
        return userRepository.saveAll(listT);
    }

    @Override
    public User findById(Long id) {
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
    public void deleteById(Long id) {
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
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
