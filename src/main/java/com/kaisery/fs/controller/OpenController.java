package com.kaisery.fs.controller;

import com.kaisery.fs.entity.User;
import com.kaisery.fs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("open")
public class OpenController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public OAuth2AccessToken login(User user) throws Exception {
        return userService.getToken(user);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public OAuth2AccessToken register(User user) throws Exception {
        userService.registerUser(user);

        return userService.getToken(user);
    }
}
