package com.spring.boot.project.demo.repository;

import com.spring.boot.project.demo.model.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from User u "
            + "order by size(u.reviews) desc, u.profileName")
    List<User> findMostActiveUsers(Pageable pageable);

    @Query("select u from User u left join fetch u.roles where u.profileName = ?1")
    User findByProfileName(String profileName);
}
