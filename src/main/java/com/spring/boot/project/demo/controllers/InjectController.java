package com.spring.boot.project.demo.controllers;

import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.model.UsersRole;
import com.spring.boot.project.demo.repository.UsersRoleRepository;
import com.spring.boot.project.demo.service.DbService;
import com.spring.boot.project.demo.service.ReviewService;
import com.spring.boot.project.demo.service.UserService;
import com.spring.boot.project.demo.service.UsersRoleService;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InjectController {
    private static final String REVIEWS_CSV_FILE_PATH
            = "src/main/resources/csv/Reviews.csv";
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UsersRoleRepository usersRoleRepository;
    private final UsersRoleService usersRoleService;
    private final ReviewService reviewService;
    private final DbService dbService;

    @PostConstruct
    public void init() {
        insertRoles();
        insertUsers();
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

    @ApiOperation(value = "Inserting data from data to DB.")
    @GetMapping("/inject-reviews-to-db")
    public String insertReviewsToDb() {
        dbService.initializeDb(REVIEWS_CSV_FILE_PATH);
        return "Success inject data to db.";
    }

    @ApiOperation(value = "Inserting test data from data to DB.")
    @GetMapping("/inject-test-reviews-to-db")
    public String insertTestReviewsToDb() {
        dbService.initializeDb("src/test/resources/tests/util/reviews_test.csv");
        return "Success inject test data to db.";
    }

    @ApiOperation(value = "Inserting test review to DB.")
    @PostMapping("/inject-reviews-to-db")
    public String insertTestReviewsToDb(Authentication authentication) {
        Review review = new Review();
        User user = userService.findByProfileName(authentication.getName());
        review.setUser(user);
        Product product = new Product();
        product.setProductId("product2");
        review.setProduct(product);
        review.setHelpfulnessNumerator(1);
        review.setHelpfulnessDenominator(1);
        review.setScore(5);
        review.setTime(LocalDateTime.now());
        review.setSummary("my users");
        review.setText("Review from authorized user text.");
        reviewService.save(review);
        return "Success inject test review to db.";
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
