package com.kaisery.fs.runner;

import com.kaisery.fs.entity.Authority;
import com.kaisery.fs.entity.OAuth2Client;
import com.kaisery.fs.entity.User;
import com.kaisery.fs.repository.OAuth2ClientRepository;
import com.kaisery.fs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

import static java.util.Collections.singleton;

@Component
public class MongoRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OAuth2ClientRepository oAuth2ClientRepository;

    @Override
    public void run(String... strings) throws Exception {

        userRepository.deleteAll();
        oAuth2ClientRepository.deleteAll();

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("12345678");
        admin.setAuthorities(singleton(Authority.ROLE_ADMIN));

        userRepository.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setPassword("12345678");
        user.setAuthorities(singleton(Authority.ROLE_USER));

        userRepository.save(user);

        OAuth2Client oAuth2Client = new OAuth2Client();
        oAuth2Client.setClientId("acme");
        oAuth2Client.setClientSecret("acmesecret");
        oAuth2Client.setAccessTokenValiditySeconds(60000);
        oAuth2Client.setRefreshTokenValiditySeconds(60000);
        oAuth2Client.setScope(new HashSet<String>(Arrays.asList("read", "write")));
        oAuth2Client.setAuthorizedGrantTypes(new HashSet<String>(Arrays.asList("authorization_code", "refresh_token", "password")));

        oAuth2ClientRepository.save(oAuth2Client);
    }
}
