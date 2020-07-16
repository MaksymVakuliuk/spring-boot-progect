package com.spring.boot.project.demo.repository;

import com.spring.boot.project.demo.model.AmazonUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmazonUserRepository extends JpaRepository<AmazonUser, String> {

}
