package com.spring.boot.project.demo.repository;

import com.spring.boot.project.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
