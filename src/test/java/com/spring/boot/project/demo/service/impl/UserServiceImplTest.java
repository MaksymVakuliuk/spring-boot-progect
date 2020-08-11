package com.spring.boot.project.demo.service.impl;

import static org.junit.Assert.assertEquals;

import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    private List<User> userList;

    @Before
    public void setup() {
        User user1 = new User();
        user1.setUserId("userId1");
        user1.setProfileName("user1");
        User user2 = new User();
        user2.setUserId("userId2");
        user2.setProfileName("user2");
        User user3 = new User();
        user3.setUserId("userId3");
        user3.setProfileName("user3");
        userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
    }

    @Test
    public void save() {
        User user = userList.get(0);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User save = userServiceImpl.save(user);
        assertEquals(user.getProfileName(), save.getProfileName());
    }

    @Test
    public void saveAll() {
        Mockito.when(userRepository.saveAll(userList)).thenReturn(userList);
        List<User> savedUserList = userServiceImpl.saveAll(userList);
        assertEquals(userList, savedUserList);
    }

    @Test
    public void findById() {
        User user = userList.get(0);
        Mockito.when(userRepository.findById("userId1"))
                .thenReturn(Optional.of(user));
        User userWithId = userServiceImpl.findById("userId1");
        assertEquals(user.getProfileName(), userWithId.getProfileName());
    }

    @Test
    public void findAll() {
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<User> usersFromDb = userServiceImpl.findAll();
        assertEquals(userList, usersFromDb);
    }

    @Test
    public void findMostActiveUsers() {
        Mockito.when(userRepository.findMostActiveUsers(PageRequest.of(0,2)))
                .thenReturn(userList);
        List<User> mostActiveUsers = userServiceImpl.findMostActiveUsers(2);
        assertEquals(userList, mostActiveUsers);
    }
}
