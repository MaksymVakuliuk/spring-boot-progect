package com.spring.boot.project.demo.dto.amazonuser;

import com.spring.boot.project.demo.model.AmazonUser;
import org.springframework.stereotype.Component;

@Component
public class AmazonUserMapper {
    public AmazonUserDto convertToAmazonUserDto(AmazonUser amazonUser) {
        AmazonUserDto amazonUserDto = new AmazonUserDto();
        amazonUserDto.setUserId(amazonUser.getUserId());
        amazonUserDto.setProfileName(amazonUser.getProfileName());
        return amazonUserDto;
    }

    public AmazonUser convertFromAmazonUserDto(AmazonUserDto amazonUserDto) {
        AmazonUser amazonUser = new AmazonUser();
        amazonUser.setUserId(amazonUserDto.getUserId());
        amazonUser.setProfileName(amazonUserDto.getProfileName());
        return amazonUser;
    }
}

