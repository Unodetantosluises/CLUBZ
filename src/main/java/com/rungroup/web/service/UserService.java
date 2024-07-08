package com.rungroup.web.service;

import com.rungroup.web.dto.UserDto;
import com.rungroup.web.models.UserEntity;

public interface UserService {
    UserEntity saveUser(UserDto userDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    UserEntity findUserById(Long userId);

    void updateUser(UserDto userDto);
}
