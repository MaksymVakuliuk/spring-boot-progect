package com.spring.boot.project.demo.service.impl;

import com.spring.boot.project.demo.model.AmazonUser;
import com.spring.boot.project.demo.repository.AmazonUserRepository;
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
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AmazonUserServiceImplTest {
    @Mock
    private AmazonUserRepository amazonUserRepository;
    @InjectMocks
    private AmazonUserServiceImpl amazonUserServiceImpl;

    private List<AmazonUser> amazonUserList;

    @Before
    public void setup() {
        AmazonUser amazonUser1 = new AmazonUser();
        amazonUser1.setUserId("userId1");
        amazonUser1.setProfileName("user1");
        AmazonUser amazonUser2 = new AmazonUser();
        amazonUser2.setUserId("userId2");
        amazonUser2.setProfileName("user2");
        AmazonUser amazonUser3 = new AmazonUser();
        amazonUser3.setUserId("userId3");
        amazonUser3.setProfileName("user3");
        amazonUserList = new ArrayList<>();
        amazonUserList.add(amazonUser1);
        amazonUserList.add(amazonUser2);
        amazonUserList.add(amazonUser3);
    }

    @Test
    public void save() {
        AmazonUser amazonUser = amazonUserList.get(0);
        Mockito.when(amazonUserRepository.save(amazonUser)).thenReturn(amazonUser);
        AmazonUser save = amazonUserServiceImpl.save(amazonUser);
        assertEquals(amazonUser.getProfileName(), save.getProfileName());
    }

    @Test
    public void saveAll() {
        Mockito.when(amazonUserRepository.saveAll(amazonUserList)).thenReturn(amazonUserList);
        List<AmazonUser> savedAmazonUserList = amazonUserServiceImpl.saveAll(amazonUserList);
        assertEquals(amazonUserList, savedAmazonUserList);
    }

    @Test
    public void findById() {
        AmazonUser amazonUser = amazonUserList.get(0);
        Mockito.when(amazonUserRepository.findById("userId1"))
                .thenReturn(Optional.of(amazonUser));
        AmazonUser userWithId = amazonUserServiceImpl.findById("userId1");
        assertEquals(amazonUser.getProfileName(), userWithId.getProfileName());
    }

    @Test
    public void findAll() {
        Mockito.when(amazonUserRepository.findAll()).thenReturn(amazonUserList);
        List<AmazonUser> amazonUsersFromDb = amazonUserServiceImpl.findAll();
        assertEquals(amazonUserList, amazonUsersFromDb);
    }

    @Test
    public void findMostActiveUsers() {
        Mockito.when(amazonUserRepository.findMostActiveUsers(PageRequest.of(0,2)))
                .thenReturn(amazonUserList);
        List<AmazonUser> mostActiveUsers = amazonUserServiceImpl.findMostActiveUsers(2);
        assertEquals(amazonUserList, mostActiveUsers);
    }
}
