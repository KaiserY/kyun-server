package com.kaisery.fs.controller;

import com.kaisery.fs.entity.User;
import com.kaisery.fs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Arrays;
import java.util.Base64;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping({ "/user", "/me" })
    public Principal user(Principal principal) {
        return principal;
    }

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
