package com.kaisery.fs.service;

import com.kaisery.fs.entity.Authority;
import com.kaisery.fs.entity.User;
import com.kaisery.fs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;
import java.util.Collections;

@Service
public class UserService {

    @Value("${oauth2.authorizationServer.uri}")
    private String oAuth2AuthorizationServerUri;

    @Autowired
    private UserRepository userRepository;

    public User findByUserName(String userName) throws Exception {
        return userRepository.findByUsername(userName).orElseThrow(() -> new Exception(String.format("User [%s]  was not found", userName)));
    }

    public User registerUser(User user) throws Exception {

        if (CollectionUtils.isEmpty(user.getAuthorities())) {
            user.setAuthorities(Collections.singleton(Authority.ROLE_USER));
        }

        return userRepository.save(user);
    }

    public OAuth2AccessToken getToken(User user) throws Exception {

        RestTemplate template = new RestTemplate();

        String Authorization = "acme:acmesecret";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString(Authorization.getBytes("UTF-8")));

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        String url = oAuth2AuthorizationServerUri + "/oauth/token";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("grant_type", "password")
            .queryParam("username", user.getUsername())
            .queryParam("password", user.getPassword());


        return template.exchange(builder.build().encode().toUri(), HttpMethod.POST, entity, OAuth2AccessToken.class).getBody();
    }
}
