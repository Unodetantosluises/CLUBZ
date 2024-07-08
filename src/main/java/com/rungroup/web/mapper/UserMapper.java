package com.rungroup.web.mapper;

import com.rungroup.web.dto.UserDto;
import com.rungroup.web.models.UserEntity;

public class UserMapper {
    public static UserEntity mapToUser(UserDto user){
        UserEntity userDto = UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .email(user.getEmail())
                .profilePhoto(user.getProfilePhoto())
                .active(user.getActive())
                .createdOn(user.getCreatedOn())
                .updatedOn(user.getUpdatedOn())
                .build();
        return userDto;
    }

    public static UserEntity mapToUserDto(UserEntity user){
        UserEntity userDto = UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .email(user.getEmail())
                .profilePhoto(user.getProfilePhoto())
                .active(user.getActive())
                .createdOn(user.getCreatedOn())
                .updatedOn(user.getUpdatedOn())
                .build();
        return userDto;
    }
}
