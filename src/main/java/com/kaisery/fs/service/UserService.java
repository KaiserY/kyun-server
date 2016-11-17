package com.kaisery.fs.service;

import com.kaisery.fs.entity.Authority;
import com.kaisery.fs.entity.Role;
import com.kaisery.fs.entity.User;
import com.kaisery.fs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUserName(String userName) throws Exception {
        return userRepository.findByUserName(userName).orElseThrow(() -> new Exception(String.format("User [%s]  was not found", userName)));
    }

    public User registerUser(User user) throws Exception {

        if (CollectionUtils.isEmpty(user.getAuthorities())) {
            user.setAuthorities(Collections.singleton(Authority.ROLE_USER));
        }

        return userRepository.save(user);
    }
}
