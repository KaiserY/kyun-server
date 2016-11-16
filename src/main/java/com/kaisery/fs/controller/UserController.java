package com.kaisery.fs.controller;

import com.kaisery.fs.entity.User;
import com.kaisery.fs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Arrays;
import java.util.Base64;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping({"/user", "/me"})
    public Principal user(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(User user) throws Exception {
        userService.registerUser(user);

        RestTemplate template = new RestTemplate();

        String Authorization = "acme:acmesecret";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString(Authorization.getBytes("UTF-8")));

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        String token = template.exchange("http://localhost:8080/oauth/token?grant_type=password&username=aa&password=123", HttpMethod.POST, entity, String.class).getBody();

        return token;
    }
}
