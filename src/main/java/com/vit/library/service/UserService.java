package com.vit.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vit.library.dto.User;
import com.vit.library.entity.UserEntity;
import com.vit.library.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void addUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress(user.getAddress());
        userEntity.setName(user.getName());
        userRepository.save(userEntity);
    }
}
