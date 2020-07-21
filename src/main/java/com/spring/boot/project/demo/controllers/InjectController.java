package com.spring.boot.project.demo.controllers;

import com.spring.boot.project.demo.model.UsersRole;
import com.spring.boot.project.demo.repository.UsersRoleRepository;
import com.spring.boot.project.demo.service.DbInitializer;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InjectController {
    private static final String REVIEWS_CSV_FILE_PATH
            = "src/test/resources/tests/util/reviews_test.csv";
    private final UsersRoleRepository usersRoleRepository;
    private final DbInitializer dbInitializer;

    @PostConstruct
    public void init() {
        insertRoles();
        insertReviewsToDb();
    }

    private void insertReviewsToDb() {
        dbInitializer.initializeDb(REVIEWS_CSV_FILE_PATH);
    }

    private void insertRoles() {
        Set<UsersRole> roles = new HashSet<>();
        UsersRole userRole = new UsersRole();
        userRole.setRoleName(UsersRole.RoleName.USER);
        roles.add(userRole);
        UsersRole adminRole = new UsersRole();
        adminRole.setRoleName(UsersRole.RoleName.ADMIN);
        roles.add(adminRole);
        usersRoleRepository.saveAll(roles);
    }
}
