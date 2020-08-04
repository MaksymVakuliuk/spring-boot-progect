package com.spring.boot.project.demo.repository;

import com.spring.boot.project.demo.model.UsersRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersRoleRepositoryTest {
    @Autowired
    private UsersRoleRepository usersRoleRepository;

    @Test
    public void findByRoleName() {
        //UsersRole admin = usersRoleRepository.findByRoleName("ADMIN");
        System.out.println("\n\n");
    }
}