package com.spring.boot.project.demo.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";
    private final UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/products/most-commented-products/**")
                .hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.GET, "/reviews/most-used-words/**").hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.GET, "/reviews/remove/**").hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.GET, "/users/most-active-users/**").hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.POST, "/reviews/save").hasRole(USER_ROLE)
                .antMatchers(HttpMethod.POST, "/reviews/update").hasRole(USER_ROLE)
                .antMatchers(HttpMethod.GET, "/register").not().authenticated()
                .antMatchers(HttpMethod.GET,"/").permitAll()
                .antMatchers(HttpMethod.GET,"/h2-console/**/").permitAll()
                .antMatchers(HttpMethod.GET,"/products/**").permitAll()
                .antMatchers(HttpMethod.GET,"/reviews/all").permitAll()
                .antMatchers(HttpMethod.GET,"/inject-reviews-to-db").permitAll()
                .antMatchers(HttpMethod.GET,"/inject-test-reviews-to-db").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
