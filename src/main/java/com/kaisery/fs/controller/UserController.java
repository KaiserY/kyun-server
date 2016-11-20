package com.kaisery.fs.controller;

import com.kaisery.fs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
