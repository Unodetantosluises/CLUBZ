package com.rungroup.web.mapper;

import com.rungroup.web.dto.UserDto;
import com.rungroup.web.models.UserEntity;

public class UserMapper {
    public static UserEnity mapToUser(UserDto user){
        UserEntity userDto = UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .profilePhoto(user.getProfilePhoto())
                .build();
        return  userDto;
    }

    public static userDto mapToUserDto()
}
