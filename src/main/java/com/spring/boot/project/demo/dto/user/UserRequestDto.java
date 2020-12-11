package com.spring.boot.project.demo.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {
    private String profileName;
    private String password;
}
