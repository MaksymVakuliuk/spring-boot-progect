package com.spring.boot.project.demo.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.spring.boot.project.demo.dto.user.UserMapper;
import com.spring.boot.project.demo.dto.user.UserRequestDto;
import com.spring.boot.project.demo.model.User;
import com.spring.boot.project.demo.model.UsersRole;
import com.spring.boot.project.demo.repository.UserRepository;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AuthenticationServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private AuthenticationServiceImpl authenticationServiceImpl;

    @Test
    public void logUpTrue() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setProfileName("testUser");
        userRequestDto.setPassword("testPass");
        Mockito.when(passwordEncoder.encode(userRequestDto.getPassword()))
                .thenReturn("encodedPass");
        User expectedUser = new User();
        expectedUser.setProfileName(userRequestDto.getProfileName());
        expectedUser.setPassword("encodePass");
        Mockito.when(userMapper.convertUserRequestDtoToUser(userRequestDto))
                .thenReturn(expectedUser);
        expectedUser.setUserId("testUserId");
        UsersRole usersRole = new UsersRole();
        usersRole.setRoleName(UsersRole.RoleName.USER);
        expectedUser.setRoles(Set.of(usersRole));
        Mockito.when(userRepository.save(expectedUser)).thenReturn(expectedUser);
        assertTrue(authenticationServiceImpl.logUp(userRequestDto));
    }

    @Test
    public void logUpFalse() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setProfileName("testUser");
        userRequestDto.setPassword("testPass");
        Mockito.when(passwordEncoder.encode(userRequestDto.getPassword()))
                .thenReturn("encodedPass");
        User expectedUser = new User();
        expectedUser.setProfileName(userRequestDto.getProfileName());
        expectedUser.setPassword("encodePass");
        Mockito.when(userMapper.convertUserRequestDtoToUser(userRequestDto))
                .thenReturn(expectedUser);
        expectedUser.setUserId("testUserId");
        UsersRole usersRole = new UsersRole();
        usersRole.setRoleName(UsersRole.RoleName.USER);
        expectedUser.setRoles(Set.of(usersRole));
        Mockito.when(userRepository.save(expectedUser)).thenReturn(null);
        assertFalse(authenticationServiceImpl.logUp(userRequestDto));
    }
}
