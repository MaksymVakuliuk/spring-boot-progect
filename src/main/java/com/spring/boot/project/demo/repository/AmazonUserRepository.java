package com.spring.boot.project.demo.repository;

import com.spring.boot.project.demo.model.AmazonUser;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AmazonUserRepository extends JpaRepository<AmazonUser, String> {
    @Query("select a from AmazonUser a order by size(a.reviews) desc")
    List<AmazonUser> getMostActiveUsers(Pageable pageable);
}
