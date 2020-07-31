package com.spring.boot.project.demo.controllers;

import com.spring.boot.project.demo.model.UsersRole;
import com.spring.boot.project.demo.repository.UsersRoleRepository;
import com.spring.boot.project.demo.service.DbService;
import io.swagger.annotations.ApiOperation;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InjectController {
    private static final String REVIEWS_CSV_FILE_PATH
            = "src/main/resources/csv/Reviews.csv";
    private final UsersRoleRepository usersRoleRepository;
    private final DbService dbService;

    @PostConstruct
    public void init() {
        insertRoles();
    }

    @ApiOperation(value = "Inserting data from data to DB.")
    @GetMapping("/inject-reviews-to-db")
    public String insertReviewsToDb() {
        dbService.initializeDb(REVIEWS_CSV_FILE_PATH);
        return "Success inject data to db.";
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
