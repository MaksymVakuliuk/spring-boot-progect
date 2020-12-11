package com.spring.boot.project.demo.service.impl;

import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.model.UsersRole;
import com.spring.boot.project.demo.service.DbService;
import com.spring.boot.project.demo.service.ProductService;
import com.spring.boot.project.demo.service.ReviewService;
import com.spring.boot.project.demo.service.UserService;
import com.spring.boot.project.demo.service.UsersRoleService;
import com.spring.boot.project.demo.unit.FileReader;
import com.spring.boot.project.demo.unit.Parser;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewDataServiceImpl implements DbService {
    private final PasswordEncoder passwordEncoder;
    private final FileReader fileReader;
    private final Parser<Review> parser;
    private final ReviewService reviewService;
    private final ProductService productService;
    private final UserService userService;
    private final UsersRoleService usersRoleService;

    @PostConstruct
    public void init() {
        insertRoles();
        insertUsers();
    }

    @Override
    public void initializeDb(String dataFilePath) {
        clearDb();
        List<String> reviewsStringList = fileReader.readLines(dataFilePath);
        List<Review> reviewsList = parser.parse(reviewsStringList);
        reviewService.saveAll(reviewsList);
    }

    @Override
    public void clearDb() {
        reviewService.deleteAll();
        productService.deleteAll();
        userService.deleteAll();
        usersRoleService.deleteAll();
        init();
    }

    private void insertUsers() {
        User admin = new User();
        admin.setProfileName("admin");
        admin.setPassword(passwordEncoder.encode("apass"));
        admin.setRoles(Set.of(usersRoleService.getRoleByName("ADMIN")));
        userService.save(admin);
        User user = new User();
        user.setProfileName("user");
        user.setPassword(passwordEncoder.encode("pass"));
        user.setRoles(Set.of(usersRoleService.getRoleByName("USER")));
        userService.save(user);
    }

    private void insertRoles() {
        Set<UsersRole> roles = new HashSet<>();
        UsersRole userRole = new UsersRole();
        userRole.setRoleName(UsersRole.RoleName.USER);
        roles.add(userRole);
        UsersRole adminRole = new UsersRole();
        adminRole.setRoleName(UsersRole.RoleName.ADMIN);
        roles.add(adminRole);
        usersRoleService.saveAll(new ArrayList<>(roles));
    }
}
