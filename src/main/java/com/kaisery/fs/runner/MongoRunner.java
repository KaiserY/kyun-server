package com.kaisery.fs.runner;

import com.kaisery.fs.entity.OAuth2Client;
import com.kaisery.fs.entity.Permission;
import com.kaisery.fs.entity.Role;
import com.kaisery.fs.entity.User;
import com.kaisery.fs.repository.OAuth2ClientRepository;
import com.kaisery.fs.repository.ResourceRepository;
import com.kaisery.fs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;

@Component
public class MongoRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private OAuth2ClientRepository oAuth2ClientRepository;

    @Override
    public void run(String... strings) throws Exception {

        userRepository.deleteAll();
        oAuth2ClientRepository.deleteAll();

        User admin = new User();
        admin.setUserName("admin");
        admin.setPassword("12345678");
        admin.setRoles(singleton(Role.ROLE_ADMIN));
        admin.setPermissions(singleton(Permission.PERMISSION_ALL));

        userRepository.save(admin);

        User user = new User();
        user.setUserName("user");
        user.setPassword("12345678");
        user.setRoles(singleton(Role.ROLE_USER));
        user.setPermissions(singleton(Permission.PERMISSION_ALL));

        userRepository.save(user);

        OAuth2Client oAuth2Client = new OAuth2Client();
        oAuth2Client.setClientId("acme");
        oAuth2Client.setClientSecret("acmesecret");
        oAuth2Client.setAccessTokenValiditySeconds(600);
        oAuth2Client.setRefreshTokenValiditySeconds(60000);
        oAuth2Client.setAuthorities(singletonList(Permission.PERMISSION_ALL));
        oAuth2Client.setScope(new HashSet<String>(Arrays.asList("read", "write")));
        oAuth2Client.setAuthorizedGrantTypes(new HashSet<String>(Arrays.asList("authorization_code", "refresh_token", "password")));

        oAuth2ClientRepository.save(oAuth2Client);
    }
}
