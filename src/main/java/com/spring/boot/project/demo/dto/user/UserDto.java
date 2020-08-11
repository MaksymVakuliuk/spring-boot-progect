package com.spring.boot.project.demo.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String profileName;
}
