package com.spring.boot.project.demo.sequrity;

import com.spring.boot.project.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var currentUser = userService.findByProfileName(userName);
        UserBuilder userBuilder;
        if (currentUser != null) {
            userBuilder = User.withUsername(userName);
            userBuilder.password(currentUser.getPassword());
            userBuilder.roles(currentUser.getRoles()
                    .stream()
                    .map(role -> role.getRoleName().toString())
                    .toArray(String[]::new));
        } else {
            throw new UsernameNotFoundException("User not found!");
        }
        return userBuilder.build();
    }
}
