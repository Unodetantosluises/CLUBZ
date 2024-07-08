package com.rungroup.web.service.impl;

import com.rungroup.web.dto.UserDto;
import com.rungroup.web.models.Role;
import com.rungroup.web.models.UserEntity;
import com.rungroup.web.repository.RoleRepository;
import com.rungroup.web.repository.UserRepository;
import com.rungroup.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.rungroup.web.mapper.UserMapper.mapToUser;
import static com.rungroup.web.mapper.UserMapper.mapToUserDto;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserEntity saveUser(UserDto userDto){
        UserEntity user = new UserEntity();
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        return user;
    }

    @Override
    public UserEntity findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity findUserById(Long userId){
        UserEntity user = userRepository.findById(userId).get();
        return mapToUserDto(user);
    }
    @Override
    public void updateUser(UserDto userDto){
        UserEntity user = mapToUser(userDto);
        userRepository.save(user);

    }
}
